package io.github.eduardoconceicao90.desafio_mv.service;

import io.github.eduardoconceicao90.desafio_mv.domain.cliente.Cliente;
import io.github.eduardoconceicao90.desafio_mv.domain.conta.enums.StatusConta;
import io.github.eduardoconceicao90.desafio_mv.domain.conta.pessoaFisica.ContaPF;
import io.github.eduardoconceicao90.desafio_mv.domain.conta.pessoaJuridica.ContaPJ;
import io.github.eduardoconceicao90.desafio_mv.infra.exception.ApiException;
import io.github.eduardoconceicao90.desafio_mv.repository.ClienteRepository;
import io.github.eduardoconceicao90.desafio_mv.repository.ContaPFRepository;
import io.github.eduardoconceicao90.desafio_mv.repository.ContaPJRepository;
import io.github.eduardoconceicao90.desafio_mv.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContaService {

    @Autowired
    private ContaPFRepository contaPFRepository;

    @Autowired
    private ContaPJRepository contaPJRepository;

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

    //------------------------------------------

    private void validarStatusCliente(Long id) throws ApiException {
        String status = clienteRepository.statusCliente(id);

        if(status.equals("ATIVO")){
            return;
        }

        throw new ApiException("Cliente está inativo.");
    }

}
