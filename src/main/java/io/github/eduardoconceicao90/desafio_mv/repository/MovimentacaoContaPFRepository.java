package io.github.eduardoconceicao90.desafio_mv.repository;

import io.github.eduardoconceicao90.desafio_mv.domain.conta.pessoaFisica.MovimentacaoContaPF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimentacaoContaPFRepository extends JpaRepository<MovimentacaoContaPF,Long> {
}
