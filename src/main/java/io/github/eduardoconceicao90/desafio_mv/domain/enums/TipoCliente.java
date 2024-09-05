package io.github.eduardoconceicao90.desafio_mv.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum TipoCliente {

    JURIDICA("Pessoa_Jurídica"),
    FISICA("Pessoa_Física");

    private String descricao;

}
