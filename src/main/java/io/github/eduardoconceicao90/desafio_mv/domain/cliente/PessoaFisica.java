package io.github.eduardoconceicao90.desafio_mv.domain.cliente;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PessoaFisica extends Cliente {

    @Column(name = "cpf", length = 11, unique = true)
    private String cpf;

}
