package io.github.eduardoconceicao90.desafio_mv.domain.conta.pessoaFisica;

import io.github.eduardoconceicao90.desafio_mv.domain.conta.enums.TipoMovimentacao;
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
public class MovimentacaoContaPF {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double valorMovimentacao;

    @Enumerated(EnumType.STRING)
    protected TipoMovimentacao tipoMovimentacao;

    private Double taxaMovimentacao;

    private LocalDateTime dataMovimentacao;

    @ManyToOne
    @JoinColumn(name = "conta_id")
    private ContaPF conta;

}
