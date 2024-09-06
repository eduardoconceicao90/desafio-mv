package io.github.eduardoconceicao90.desafio_mv.service;

import io.github.eduardoconceicao90.desafio_mv.domain.cliente.Cliente;
import io.github.eduardoconceicao90.desafio_mv.domain.cliente.PessoaFisica;
import io.github.eduardoconceicao90.desafio_mv.domain.cliente.PessoaJuridica;
import io.github.eduardoconceicao90.desafio_mv.domain.cliente.dto.ClienteDTO;
import io.github.eduardoconceicao90.desafio_mv.domain.cliente.enums.TipoCliente;
import io.github.eduardoconceicao90.desafio_mv.repository.ClienteRepository;
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

    public Cliente findById(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id ));
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public PessoaFisica cadastrarPessoaFisica(PessoaFisica pessoa) {
        var cliente = new ClienteDTO(null, pessoa.getCpf(), null);
        findByDocumento(cliente);

        pessoa.setTipoCliente(TipoCliente.PESSOA_FISICA);

        return clienteRepository.save(pessoa);
    }

    public PessoaJuridica cadastrarPessoaJuridica(PessoaJuridica pessoa) {
        var cliente = new ClienteDTO(null, null, pessoa.getCnpj());
        findByDocumento(cliente);

        pessoa.setTipoCliente(TipoCliente.PESSOA_JURIDICA);

        return clienteRepository.save(pessoa);
    }

    public PessoaFisica atualizarPessoaFisica(PessoaFisica pessoa, Long id) {
        pessoa.setId(id);
        PessoaFisica pessoaSalva = (PessoaFisica) findById(id);

        var cliente = new ClienteDTO(id, pessoaSalva.getCpf(), null);
        findByDocumento(cliente);

        mapper.getConfiguration().setSkipNullEnabled(true);
        mapper.map(pessoa, pessoaSalva);

        return clienteRepository.save(pessoaSalva);
    }

    public PessoaJuridica atualizarPessoaJuridica(PessoaJuridica pessoa, Long id) {
        pessoa.setId(id);
        PessoaJuridica pessoaSalva = (PessoaJuridica) findById(id);

        var cliente = new ClienteDTO(id, null, pessoaSalva.getCnpj());
        findByDocumento(cliente);

        mapper.getConfiguration().setSkipNullEnabled(true);
        mapper.map(pessoa, pessoaSalva);

        return clienteRepository.save(pessoaSalva);
    }

    public void deletarCliente(Long id) {
        findById(id);
        clienteRepository.deleteById(id);
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
