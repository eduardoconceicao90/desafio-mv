package io.github.eduardoconceicao90.desafio_mv.repository;

import io.github.eduardoconceicao90.desafio_mv.domain.cliente.Cliente;
import io.github.eduardoconceicao90.desafio_mv.domain.cliente.PessoaFisica;
import io.github.eduardoconceicao90.desafio_mv.domain.cliente.PessoaJuridica;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {

    @Query("SELECT p FROM PessoaFisica p WHERE p.cpf =:cpf")
    Optional<PessoaFisica> findByCpf(String cpf);

    @Query("SELECT p FROM PessoaJuridica p WHERE p.cnpj =:cnpj")
    Optional<PessoaJuridica> findByCnpj(String cnpj);

    @Query(value = "SELECT status_cliente FROM Cliente WHERE id = ?1", nativeQuery = true)
    String statusCliente(Long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Cliente SET status_cliente = ?1 where id = ?2", nativeQuery = true)
    void alterarStatusCliente(String status, Long id);

    String consultaSaldoCliente = """
                        SELECT
                            CLI.NOME,
                            CLI.TIPO_CLIENTE,
                            CLI.DATA_CADASTRO,
                            CLI.LOGRADOURO,
                            CLI.NUMERO,
                            CLI.COMPLEMENTO,
                            CLI.BAIRRO,
                            CLI.CIDADE,
                            CLI.CEP,
                            CLI.UF,
                            PF.QTD_MOVIMENTACAO_DEBITO QTD_MOVIMENTACAO_DEBITO_PF,
                            PF.QTD_MOVIMENTACAO_CREDITO QTD_MOVIMENTACAO_CREDITO_PF,
                            PJ.QTD_MOVIMENTACAO_DEBITO QTD_MOVIMENTACAO_DEBITO_PJ,
                            PJ.QTD_MOVIMENTACAO_CREDITO QTD_MOVIMENTACAO_CREDITO_PJ,
                            PF.QTD_MOVIMENTACAO_DEBITO + PF.QTD_MOVIMENTACAO_CREDITO TOTAL_MOVIMENTACAO_PF,
                            PJ.QTD_MOVIMENTACAO_DEBITO + PJ.QTD_MOVIMENTACAO_CREDITO TOTAL_MOVIMENTACAO_PJ,
                            PF.SALDO SALDO_PF,
                            PJ.SALDO SALDO_PJ,
                            (SELECT SUM(MPJ.TAXA_MOVIMENTACAO)
                             FROM MOVIMENTACAO_CONTAPJ MPJ
                             WHERE MPJ.CONTA_ID = PJ.ID) AS TAXA_MOVIMENTACAO_PJ,
                            (SELECT SUM(MPF.TAXA_MOVIMENTACAO)
                             FROM MOVIMENTACAO_CONTAPF MPF
                             WHERE MPF.CONTA_ID = PF.ID) AS TAXA_MOVIMENTACAO_PF
                        FROM CLIENTE CLI
                        LEFT JOIN CONTAPF PF ON PF.CLIENTE_ID = CLI.ID
                        LEFT JOIN CONTAPJ PJ ON PJ.CLIENTE_ID = CLI.ID
                        WHERE CLI.ID = ?
                    """;

}
