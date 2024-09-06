package io.github.eduardoconceicao90.desafio_mv.domain.cliente.dto;

import io.github.eduardoconceicao90.desafio_mv.domain.cliente.Endereco;
import io.github.eduardoconceicao90.desafio_mv.domain.cliente.enums.TipoCliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

    private Long id;
    private String nome;
    private String cpf;
    private String cnpj;
    private String telefone;
    private TipoCliente tipoCliente;
    private Endereco endereco;

}
