package io.github.eduardoconceicao90.desafio_mv.service;

import io.github.eduardoconceicao90.desafio_mv.domain.cliente.Cliente;
import io.github.eduardoconceicao90.desafio_mv.domain.conta.enums.StatusConta;
import io.github.eduardoconceicao90.desafio_mv.domain.conta.enums.TipoMovimentacao;
import io.github.eduardoconceicao90.desafio_mv.domain.conta.pessoaFisica.ContaPF;
import io.github.eduardoconceicao90.desafio_mv.domain.conta.pessoaFisica.MovimentacaoContaPF;
import io.github.eduardoconceicao90.desafio_mv.domain.conta.pessoaJuridica.ContaPJ;
import io.github.eduardoconceicao90.desafio_mv.domain.conta.pessoaJuridica.MovimentacaoContaPJ;
import io.github.eduardoconceicao90.desafio_mv.infra.exception.ApiException;
import io.github.eduardoconceicao90.desafio_mv.repository.*;
import io.github.eduardoconceicao90.desafio_mv.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ContaService {

    @Autowired
    private ContaPFRepository contaPFRepository;

    @Autowired
    private ContaPJRepository contaPJRepository;

    @Autowired
    private MovimentacaoContaPFRepository movimentacaoContaPFRepository;

    @Autowired
    private MovimentacaoContaPJRepository movimentacaoContaPJRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteService clienteService;

    //----------------------- CONTA PESSOA FISICA:

    public ContaPF findContaPFById(Long id) {
        Optional<ContaPF> conta = contaPFRepository.findById(id);
        return conta.orElseThrow(() -> new ObjectNotFoundException("Conta não encontrada! Id: " + id ));
    }

    public ContaPF cadastrarContaPF(ContaPF conta) throws ApiException {
        Cliente cliente = clienteService.findById(conta.getPessoaFisica().getId());
        validarStatusCliente(cliente.getId());

        if(cliente.getTipoCliente().name().equals("PESSOA_FISICA")){
            String statusConta = contaPFRepository.statusConta(cliente.getId());

            if(statusConta == null || !statusConta.equals("ATIVA")){
                conta.setStatusConta(StatusConta.ATIVA);
                return contaPFRepository.save(conta);
            }else {
                throw new ApiException("Cliente já possui uma conta ativa.");
            }

        }else {
            throw new ApiException("Cliente não é PESSOA FÍSICA.");
        }
    }

    public void inativarContaPF(Long id){
        contaPFRepository.alterarStatusConta(StatusConta.INATIVA.toString(), id);
    }

    public void ativarContaPF(Long id){
        contaPFRepository.alterarStatusConta(StatusConta.ATIVA.toString(), id);
    }

    public Double valorTaxaPF(Long clienteId){
        ContaPF conta = findContaPFById(clienteId);

        Double taxa = null;

        if(conta.getQtdMovimentacaoDebito() <= 10){
            taxa = 1.00;
        }else if (conta.getQtdMovimentacaoDebito() <= 20){
            taxa = 0.75;
        }else{
            taxa = 0.50;
        }

        return taxa;
    }

    //----------------------- CONTA PESSOA JURIDICA:

    public ContaPJ findContaPJById(Long id) {
        Optional<ContaPJ> conta = contaPJRepository.findById(id);
        return conta.orElseThrow(() -> new ObjectNotFoundException("Conta não encontrada! Id: " + id ));
    }

    public ContaPJ cadastrarContaPJ(ContaPJ conta) throws ApiException {
        Cliente cliente = clienteService.findById(conta.getPessoaJuridica().getId());
        validarStatusCliente(cliente.getId());

        if(cliente.getTipoCliente().name().equals("PESSOA_JURIDICA")){
            String statusConta = contaPJRepository.statusConta(cliente.getId());

            if(statusConta == null || !statusConta.equals("ATIVA")){
                conta.setStatusConta(StatusConta.ATIVA);
                return contaPJRepository.save(conta);
            } else {
                throw new ApiException("Cliente já possui uma conta ativa.");
            }

        }else {
            throw new ApiException("Cliente não é PESSOA JURÍDICA.");
        }
    }

    public void inativarContaPJ(Long id){
        contaPJRepository.alterarStatusConta(StatusConta.INATIVA.toString(), id);
    }

    public void ativarContaPJ(Long id){
        contaPJRepository.alterarStatusConta(StatusConta.ATIVA.toString(), id);
    }

    public Double valorTaxaPJ(Long clienteId){
        ContaPJ conta = findContaPJById(clienteId);

        Double taxa = null;

        if(conta.getQtdMovimentacaoDebito() <= 10){
            taxa = 1.00;
        }else if (conta.getQtdMovimentacaoDebito() <= 20){
            taxa = 0.75;
        }else{
            taxa = 0.50;
        }

        return taxa;
    }

    //------------------------------------------

    private void validarStatusCliente(Long id) throws ApiException {
        String status = clienteRepository.statusCliente(id);

        if(status.equals("ATIVO")){
            return;
        }

        throw new ApiException("Cliente está inativo.");
    }

    public void transferirSaldo(Double valor, Long clienteIdDebito, Long contaIdDebito, Long clienteIdCredito, Long contaIdCredito) throws ApiException {
        Cliente cliente = clienteService.findById(clienteIdDebito);

        if(cliente.getTipoCliente().name().equals("PESSOA_FISICA")) {
            ContaPF c = findContaPFById(contaIdDebito);

            if(c.getSaldo() < valor){
                throw new ApiException("Saldo insuficiente.");
            }

            Double taxa = valorTaxaPF(contaIdDebito);
            LocalDateTime dataAtual = LocalDateTime.now();

            ContaPF conta = new ContaPF();
            MovimentacaoContaPF movimentacao = new MovimentacaoContaPF();

            conta.setId(contaIdDebito);
            movimentacao.setConta(conta);
            movimentacao.setTaxaMovimentacao(taxa);
            movimentacao.setValorMovimentacao(valor);
            movimentacao.setTipoMovimentacao(TipoMovimentacao.DEBITO);
            movimentacao.setDataMovimentacao(dataAtual);

            contaPFRepository.adicionarMovimentacaoDebito(clienteIdDebito);
            contaPFRepository.removerSaldoConta(valor, clienteIdDebito);

            receberSaldo(valor, clienteIdCredito, contaIdCredito);

            movimentacaoContaPFRepository.save(movimentacao);

        }else {
            ContaPJ c = findContaPJById(contaIdDebito);

            if(c.getSaldo() < valor){
                throw new ApiException("Saldo insuficiente.");
            }

            Double taxa = valorTaxaPJ(contaIdDebito);
            LocalDateTime dataAtual = LocalDateTime.now();

            ContaPJ conta = new ContaPJ();
            MovimentacaoContaPJ movimentacao = new MovimentacaoContaPJ();

            conta.setId(contaIdDebito);
            movimentacao.setConta(conta);
            movimentacao.setTaxaMovimentacao(taxa);
            movimentacao.setValorMovimentacao(valor);
            movimentacao.setTipoMovimentacao(TipoMovimentacao.DEBITO);
            movimentacao.setDataMovimentacao(dataAtual);

            contaPJRepository.adicionarMovimentacaoDebito(clienteIdDebito);
            contaPJRepository.removerSaldoConta(valor, clienteIdDebito);

            receberSaldo(valor, clienteIdCredito, contaIdCredito);

            movimentacaoContaPJRepository.save(movimentacao);
        }

    }

    public void receberSaldo(Double valor, Long clienteIdCredito, Long contaIdCredito){
        Cliente cliente = clienteService.findById(clienteIdCredito);

        if(cliente.getTipoCliente().name().equals("PESSOA_FISICA")) {
            Double taxa = 0.0;
            LocalDateTime dataAtual = LocalDateTime.now();

            ContaPF conta = new ContaPF();
            MovimentacaoContaPF movimentacao = new MovimentacaoContaPF();

            conta.setId(contaIdCredito);
            movimentacao.setConta(conta);
            movimentacao.setTaxaMovimentacao(taxa);
            movimentacao.setValorMovimentacao(valor);
            movimentacao.setTipoMovimentacao(TipoMovimentacao.CREDITO);
            movimentacao.setDataMovimentacao(dataAtual);

            contaPFRepository.adicionarMovimentacaoCredito(clienteIdCredito);
            contaPFRepository.adicionarSaldoConta(valor, clienteIdCredito);

            movimentacaoContaPFRepository.save(movimentacao);

        }else {
            Double taxa = 0.0;
            LocalDateTime dataAtual = LocalDateTime.now();

            ContaPJ conta = new ContaPJ();
            MovimentacaoContaPJ movimentacao = new MovimentacaoContaPJ();

            conta.setId(contaIdCredito);
            movimentacao.setConta(conta);
            movimentacao.setTaxaMovimentacao(taxa);
            movimentacao.setValorMovimentacao(valor);
            movimentacao.setTipoMovimentacao(TipoMovimentacao.CREDITO);
            movimentacao.setDataMovimentacao(dataAtual);

            contaPJRepository.adicionarMovimentacaoCredito(clienteIdCredito);
            contaPJRepository.adicionarSaldoConta(valor, clienteIdCredito);

            movimentacaoContaPJRepository.save(movimentacao);
        }

    }

}
