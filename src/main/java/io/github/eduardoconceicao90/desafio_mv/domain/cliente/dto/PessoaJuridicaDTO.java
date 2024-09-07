package io.github.eduardoconceicao90.desafio_mv.domain.cliente.dto;

import io.github.eduardoconceicao90.desafio_mv.domain.cliente.Endereco;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Hidden
public class PessoaJuridicaDTO {

    private Long id;
    private String nome;
    private String telefone;

    @Valid
    private Endereco endereco;

}
