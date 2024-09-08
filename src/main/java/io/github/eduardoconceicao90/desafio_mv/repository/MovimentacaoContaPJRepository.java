package io.github.eduardoconceicao90.desafio_mv.repository;

import io.github.eduardoconceicao90.desafio_mv.domain.conta.pessoaJuridica.MovimentacaoContaPJ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimentacaoContaPJRepository extends JpaRepository<MovimentacaoContaPJ,Long> {
}
