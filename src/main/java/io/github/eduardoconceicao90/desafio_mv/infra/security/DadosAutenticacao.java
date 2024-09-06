package io.github.eduardoconceicao90.desafio_mv.infra.security;

import jakarta.validation.constraints.NotEmpty;

public record DadosAutenticacao(

        @NotEmpty(message = "{campo.login.obrigatorio}")
        String login,

        @NotEmpty(message = "{campo.senha.obrigatorio}")
        String senha

) {
}
