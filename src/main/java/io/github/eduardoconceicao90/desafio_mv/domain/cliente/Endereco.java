package io.github.eduardoconceicao90.desafio_mv.domain.cliente;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
public class Endereco {

    @NotBlank(message = "{campo.logradouro.obrigatorio}")
    private String logradouro;

    @NotBlank(message = "{campo.bairro.obrigatorio}")
    private String bairro;

    @NotBlank(message = "{campo.cep.obrigatorio}")
    private String cep;

    @NotBlank(message = "{campo.numero.obrigatorio}")
    private String numero;

    private String complemento;

    @NotBlank(message = "{campo.cidade.obrigatorio}")
    private String cidade;

    @NotBlank(message = "{campo.uf.obrigatorio}")
    private String uf;

}
