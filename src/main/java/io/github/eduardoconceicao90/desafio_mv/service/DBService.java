package io.github.eduardoconceicao90.desafio_mv.service;

import io.github.eduardoconceicao90.desafio_mv.domain.cliente.Endereco;
import io.github.eduardoconceicao90.desafio_mv.domain.cliente.PessoaFisica;
import io.github.eduardoconceicao90.desafio_mv.domain.cliente.PessoaJuridica;
import io.github.eduardoconceicao90.desafio_mv.domain.cliente.enums.StatusCliente;
import io.github.eduardoconceicao90.desafio_mv.domain.cliente.enums.TipoCliente;
import io.github.eduardoconceicao90.desafio_mv.domain.conta.enums.StatusConta;
import io.github.eduardoconceicao90.desafio_mv.domain.conta.pessoaFisica.ContaPF;
import io.github.eduardoconceicao90.desafio_mv.domain.conta.pessoaJuridica.ContaPJ;
import io.github.eduardoconceicao90.desafio_mv.infra.security.user.UserSS;
import io.github.eduardoconceicao90.desafio_mv.repository.ClienteRepository;
import io.github.eduardoconceicao90.desafio_mv.repository.ContaPFRepository;
import io.github.eduardoconceicao90.desafio_mv.repository.ContaPJRepository;
import io.github.eduardoconceicao90.desafio_mv.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ContaPFRepository contaPFRepository;

    @Autowired
    private ContaPJRepository contaPJRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Bean
    public void instanciaDB() {

        UserSS user = new UserSS(null, "administrador", encoder.encode("123"));

        PessoaFisica p1 = new PessoaFisica();
        Endereco e1 = new Endereco("Rua 1", "Casa Forte", "12345678", "230", "Bl J, Apto 301", "Recife", "PE");
        p1.setId(null);
        p1.setNome("Eduardo Conceição");
        p1.setCpf("92245449088");
        p1.setTelefone("81 983784049");
        p1.setTipoCliente(TipoCliente.PESSOA_FISICA);
        p1.setStatusCliente(StatusCliente.ATIVO);
        p1.setEndereco(e1);

        PessoaFisica p2 = new PessoaFisica();
        Endereco e2 = new Endereco("Rua Lobato", "Timbi", "12345678", "10", "Casa A", "Camaragibe", "PE");
        p2.setId(null);
        p2.setNome("Telma Maria");
        p2.setCpf("91478586052");
        p2.setTelefone("81 985223366");
        p2.setTipoCliente(TipoCliente.PESSOA_FISICA);
        p2.setStatusCliente(StatusCliente.ATIVO);
        p2.setEndereco(e2);

        PessoaJuridica p3 = new PessoaJuridica();
        Endereco e3 = new Endereco("Rua Nunes", "Boa Viagem", "12345678", "159", "Sala 302", "Recife", "PE");
        p3.setId(null);
        p3.setNome("Tech Brasil");
        p3.setCnpj("80837508000188");
        p3.setTelefone("81 32569988");
        p3.setTipoCliente(TipoCliente.PESSOA_JURIDICA);
        p3.setStatusCliente(StatusCliente.ATIVO);
        p3.setEndereco(e3);

        ContaPF c1 = new ContaPF();
        c1.setSaldo(1000.0);
        c1.setPessoaFisica(p1);
        c1.setStatusConta(StatusConta.ATIVA);

        ContaPF c2 = new ContaPF();
        c2.setSaldo(75.5);
        c2.setPessoaFisica(p2);
        c2.setStatusConta(StatusConta.ATIVA);

        ContaPJ c3 = new ContaPJ();
        c3.setSaldo(200000.0);
        c3.setPessoaJuridica(p3);
        c3.setStatusConta(StatusConta.ATIVA);

        clienteRepository.saveAll(Arrays.asList(p1, p2,p3));
        contaPFRepository.saveAll(Arrays.asList(c1, c2));
        contaPJRepository.save(c3);
        usuarioRepository.save(user);

    }

}