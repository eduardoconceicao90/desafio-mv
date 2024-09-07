package io.github.eduardoconceicao90.desafio_mv.repository;

import io.github.eduardoconceicao90.desafio_mv.domain.conta.pessoaJuridica.ContaPJ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaPJRepository extends JpaRepository<ContaPJ,Long> {

    @Query(value = "SELECT status_conta FROM ContaPJ WHERE cliente_id = ?1", nativeQuery = true)
    String statusConta(Long id);

}
