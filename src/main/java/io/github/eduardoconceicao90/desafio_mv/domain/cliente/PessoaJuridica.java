package io.github.eduardoconceicao90.desafio_mv.domain.cliente;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

@Getter
@Setter
@Entity
public class PessoaJuridica extends Cliente {

    @Column(name = "cnpj", length = 15, unique = true)
    @NotBlank(message = "{campo.cnpj.obrigatorio}")
    @CNPJ(message = "{campo.cnpj.invalido}")
    private String cnpj;

}
