package io.github.eduardoconceicao90.desafio_mv.domain.cliente.dto;

import io.github.eduardoconceicao90.desafio_mv.domain.cliente.Endereco;
import io.github.eduardoconceicao90.desafio_mv.domain.cliente.enums.TipoCliente;
import io.github.eduardoconceicao90.desafio_mv.domain.conta.Conta;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaFisicaDTO {

    private  Long id;

    @NotBlank(message = "{campo.nome.obrigatorio}")
    private String nome;

    @NotBlank(message = "{campo.cpf.obrigatorio}")
    @CPF(message = "{campo.cpf.invalido}")
    private String cpf;

    @NotBlank(message = "{campo.telefone.obrigatorio}")
    private String telefone;

    private TipoCliente tipoCliente;

    private List<Conta> contas = new ArrayList<>();

    @NotNull(message = "{campo.endereco.obrigatorio}")
    @Valid
    private Endereco endereco;

}
