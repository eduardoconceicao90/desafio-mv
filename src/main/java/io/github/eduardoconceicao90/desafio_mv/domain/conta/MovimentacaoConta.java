package io.github.eduardoconceicao90.desafio_mv.domain.conta;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
@Entity
public class MovimentacaoConta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal movimentacaoDebito;

    private BigDecimal movimentacaoCredito;

    private LocalDateTime dataMovimentacao;

    @ManyToOne
    @JoinColumn(name = "conta_id")
    private Conta conta;
}
