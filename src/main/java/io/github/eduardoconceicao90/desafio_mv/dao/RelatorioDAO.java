package io.github.eduardoconceicao90.desafio_mv.dao;

import io.github.eduardoconceicao90.desafio_mv.domain.cliente.Endereco;
import io.github.eduardoconceicao90.desafio_mv.domain.relatorio.SaldoCliente;
import io.github.eduardoconceicao90.desafio_mv.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class RelatorioDAO {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public SaldoCliente getSaldoCliente(Long id) {

        String sql = clienteRepository.consultaSaldoCliente;

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {

            SaldoCliente saldo = new SaldoCliente();
            Endereco endereco = new Endereco();

            saldo.setNome(rs.getString("NOME"));
            saldo.setTipoCliente(rs.getString("TIPO_CLIENTE"));
            saldo.setDataCadastro(rs.getDate("DATA_CADASTRO").toLocalDate());

            endereco.setLogradouro(rs.getString("LOGRADOURO"));
            endereco.setNumero(rs.getString("NUMERO"));
            endereco.setComplemento(rs.getString("COMPLEMENTO"));
            endereco.setBairro(rs.getString("BAIRRO"));
            endereco.setCidade(rs.getString("CIDADE"));
            endereco.setCep(rs.getString("CEP"));
            endereco.setUf(rs.getString("UF"));

            saldo.setEndereco(endereco);

            return saldo;

        }, id);
    }
}
