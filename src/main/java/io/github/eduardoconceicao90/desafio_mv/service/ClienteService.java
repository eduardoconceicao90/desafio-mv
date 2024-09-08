package io.github.eduardoconceicao90.desafio_mv.service;

import io.github.eduardoconceicao90.desafio_mv.domain.cliente.Cliente;
import io.github.eduardoconceicao90.desafio_mv.domain.cliente.PessoaFisica;
import io.github.eduardoconceicao90.desafio_mv.domain.cliente.PessoaJuridica;
import io.github.eduardoconceicao90.desafio_mv.domain.cliente.dto.ClienteDTO;
import io.github.eduardoconceicao90.desafio_mv.domain.cliente.dto.PessoaFisicaDTO;
import io.github.eduardoconceicao90.desafio_mv.domain.cliente.dto.PessoaJuridicaDTO;
import io.github.eduardoconceicao90.desafio_mv.domain.cliente.enums.StatusCliente;
import io.github.eduardoconceicao90.desafio_mv.domain.cliente.enums.TipoCliente;
import io.github.eduardoconceicao90.desafio_mv.repository.ClienteRepository;
import io.github.eduardoconceicao90.desafio_mv.repository.ContaPFRepository;
import io.github.eduardoconceicao90.desafio_mv.repository.ContaPJRepository;
import io.github.eduardoconceicao90.desafio_mv.service.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ContaPFRepository contaPFRepository;

    @Autowired
    private ContaPJRepository contaPJRepository;

    public Cliente findById(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado! Id: " + id ));
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    //----------------------- CLIENTE PESSOA FISICA:

    public PessoaFisica cadastrarPessoaFisica(PessoaFisica pessoa) {
        var cliente = new ClienteDTO(null, pessoa.getCpf(), null);
        findByDocumento(cliente);

        pessoa.setStatusCliente(StatusCliente.ATIVO);
        pessoa.setTipoCliente(TipoCliente.PESSOA_FISICA);
        return clienteRepository.save(pessoa);
    }

    public PessoaFisica atualizarPessoaFisica(PessoaFisicaDTO pessoa, Long id) {
        PessoaFisica pessoaSalva = (PessoaFisica) findById(id);

        var cliente = new ClienteDTO(id, pessoaSalva.getCpf(), null);
        findByDocumento(cliente);

        mapper.getConfiguration().setSkipNullEnabled(true);
        mapper.map(pessoa, pessoaSalva);

        return clienteRepository.save(pessoaSalva);
    }

    //----------------------- CLIENTE PESSOA JURIDICA:

    public PessoaJuridica cadastrarPessoaJuridica(PessoaJuridica pessoa) {
        var cliente = new ClienteDTO(null, null, pessoa.getCnpj());
        findByDocumento(cliente);

        pessoa.setStatusCliente(StatusCliente.ATIVO);
        pessoa.setTipoCliente(TipoCliente.PESSOA_JURIDICA);
        return clienteRepository.save(pessoa);
    }

    public PessoaJuridica atualizarPessoaJuridica(PessoaJuridicaDTO pessoa, Long id) {
        PessoaJuridica pessoaSalva = (PessoaJuridica) findById(id);

        var cliente = new ClienteDTO(id, null, pessoaSalva.getCnpj());
        findByDocumento(cliente);

        mapper.getConfiguration().setSkipNullEnabled(true);
        mapper.map(pessoa, pessoaSalva);

        return clienteRepository.save(pessoaSalva);
    }

    //------------------------------------------

    public void deletarOuInativarCliente(Long id) {
       Cliente cliente = findById(id);

        if(cliente.getTipoCliente().name().equals("PESSOA_FISICA")) {

            String statusConta = contaPFRepository.statusConta(cliente.getId());

            if(statusConta != null){
                clienteRepository.alterarStatusCliente(StatusCliente.INATIVO.toString(), id);
            }else {
                clienteRepository.deleteById(id);
            }

        } else if(cliente.getTipoCliente().name().equals("PESSOA_JURIDICA")) {

            String statusConta = contaPJRepository.statusConta(cliente.getId());

            if (statusConta != null) {
                clienteRepository.alterarStatusCliente(StatusCliente.INATIVO.toString(), id);
            }else {
                clienteRepository.deleteById(id);
            }

        }

    }

    public void ativarCliente(Long id){
        clienteRepository.alterarStatusCliente(StatusCliente.ATIVO.toString(), id);
    }

    //------------------------------------------

    private void findByDocumento(ClienteDTO cliente) {
        Optional<PessoaFisica> clientePessoaFisica = clienteRepository.findByCpf(cliente.getCpf());

        if(clientePessoaFisica.isPresent() && !clientePessoaFisica.get().getId().equals(cliente.getId())) {
            throw new DataIntegrityViolationException("CPF já cadastrado!");
        }

        else {
            Optional<PessoaJuridica> clientePessoaJuridica = clienteRepository.findByCnpj(cliente.getCnpj());

            if(clientePessoaJuridica.isPresent() && !clientePessoaJuridica.get().getId().equals(cliente.getId())) {
                throw new DataIntegrityViolationException("CNPJ já cadastrado!");
            }
        }
    }
}