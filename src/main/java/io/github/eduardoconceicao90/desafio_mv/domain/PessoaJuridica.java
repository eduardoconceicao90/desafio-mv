package io.github.eduardoconceicao90.desafio_mv.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

@Getter
@Setter
@Entity
public class PessoaJuridica extends Cliente {

    @CNPJ
    @Column(unique = true)
    private String cpf;

}
