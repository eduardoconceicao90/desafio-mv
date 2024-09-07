package io.github.eduardoconceicao90.desafio_mv.domain.cliente;

import io.github.eduardoconceicao90.desafio_mv.domain.cliente.enums.TipoCliente;
import io.github.eduardoconceicao90.desafio_mv.domain.conta.Conta;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    protected LocalDateTime dataCadastro = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    protected TipoCliente tipoCliente;

    @OneToMany(mappedBy = "cliente")
    protected List<Conta> contas = new ArrayList<>();

    @Valid
    @Embedded
    protected Endereco endereco;

}
