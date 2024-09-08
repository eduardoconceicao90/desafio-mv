package io.github.eduardoconceicao90.desafio_mv.repository;

import io.github.eduardoconceicao90.desafio_mv.domain.conta.pessoaFisica.ContaPF;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaPFRepository extends JpaRepository<ContaPF,Long> {

    @Query(value = "SELECT status_conta FROM ContaPF WHERE cliente_id = ?1", nativeQuery = true)
    String statusConta(Long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE ContaPF SET status_conta = ?1 WHERE cliente_id = ?2", nativeQuery = true)
    void alterarStatusConta(String status, Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE ContaPF SET qtd_movimentacao_debito = qtd_movimentacao_debito + 1 WHERE cliente_id = ?1", nativeQuery = true)
    void adicionarMovimentacaoDebito(Long cliente_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE ContaPF SET saldo = saldo - ?1 WHERE cliente_id = ?2", nativeQuery = true)
    void removerSaldoConta(Double valor, Long cliente_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE ContaPF SET qtd_movimentacao_credito = qtd_movimentacao_credito + 1 WHERE cliente_id = ?1", nativeQuery = true)
    void adicionarMovimentacaoCredito(Long cliente_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE ContaPF SET saldo = saldo + ?1 WHERE cliente_id = ?2", nativeQuery = true)
    void adicionarSaldoConta(Double valor, Long cliente_id);

}
