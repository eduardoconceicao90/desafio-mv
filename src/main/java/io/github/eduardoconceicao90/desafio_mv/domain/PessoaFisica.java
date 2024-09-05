package io.github.eduardoconceicao90.desafio_mv.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@Entity
public class PessoaFisica extends Cliente {

    @CPF
    @Column(unique = true)
    private String cpf;

}
