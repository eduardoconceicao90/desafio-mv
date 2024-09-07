package io.github.eduardoconceicao90.desafio_mv.repository;

import io.github.eduardoconceicao90.desafio_mv.domain.conta.pessoaFisica.ContaPF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaPFRepository extends JpaRepository<ContaPF,Long> {

    @Query(value = "SELECT status_conta FROM ContaPF WHERE cliente_id = ?1", nativeQuery = true)
    String statusConta(Long id);

}
