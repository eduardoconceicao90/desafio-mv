package io.github.eduardoconceicao90.desafio_mv.domain.relatorio;

import io.github.eduardoconceicao90.desafio_mv.domain.cliente.Endereco;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SaldoCliente {

    private String nome;
    private String tipoCliente;
    private LocalDate dataCadastro;
    private Endereco endereco;
    private Long qtdMovimentacaoDebito;
    private Long qtdMovimentacaoCredito;
    private Long totalMovimentacao;
    private Double saldoInicial;
    private Double saldoAtual;

}
