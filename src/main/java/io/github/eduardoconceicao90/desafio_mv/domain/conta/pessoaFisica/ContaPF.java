package io.github.eduardoconceicao90.desafio_mv.domain.conta.pessoaFisica;

import io.github.eduardoconceicao90.desafio_mv.domain.cliente.PessoaFisica;
import io.github.eduardoconceicao90.desafio_mv.domain.conta.enums.StatusConta;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
@Entity
public class ContaPF {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "{campo.saldo.obrigatorio}")
    private Double saldo;

    @Enumerated(EnumType.STRING)
    protected StatusConta statusConta;

    private Long qtdMovimentacaoDebito = (long) 0;

    private Long qtdMovimentacaoCredito = (long) 0;

    private LocalDate dataCadastro = LocalDate.now();

    @NotNull(message = "{campo.cliente.obrigatorio}")
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private PessoaFisica pessoaFisica;

}
