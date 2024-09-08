package io.github.eduardoconceicao90.desafio_mv.domain.conta.pessoaJuridica;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
@Entity
public class MovimentacaoContaPJ {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double valorMovimentacaoDebito;

    private Double valorMovimentacaoCredito;

    private Double taxaMovimentacao;

    private LocalDateTime dataMovimentacao;

    @ManyToOne
    @JoinColumn(name = "conta_id")
    private ContaPJ conta;

}
