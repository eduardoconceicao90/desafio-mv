package io.github.eduardoconceicao90.desafio_mv.domain.cliente;

import io.github.eduardoconceicao90.desafio_mv.domain.cliente.enums.TipoCliente;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
public abstract class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "nome", length = 100)
    protected String nome;

    @Column(name = "telefone", length = 12)
    protected String telefone;

    protected LocalDate dataCadastro = LocalDate.now();

    @Enumerated(EnumType.STRING)
    protected TipoCliente tipoCliente;

    @Valid
    @Embedded
    protected Endereco endereco;

}
