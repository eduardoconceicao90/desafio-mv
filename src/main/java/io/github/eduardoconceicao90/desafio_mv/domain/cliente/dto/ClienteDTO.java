package io.github.eduardoconceicao90.desafio_mv.domain.cliente.dto;

import io.github.eduardoconceicao90.desafio_mv.domain.cliente.Endereco;
import io.github.eduardoconceicao90.desafio_mv.domain.cliente.enums.TipoCliente;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Hidden
public class ClienteDTO {

    private Long id;
    private String nome;
    private String cpf;
    private String cnpj;
    private String telefone;
    private TipoCliente tipoCliente;
    private Endereco endereco;

    public ClienteDTO(Long id, String cpf, String cnpj) {
        this.id = id;
        this.cpf = cpf;
        this.cnpj = cnpj;
    }

}
