package io.github.eduardoconceicao90.desafio_mv.domain.cliente;

import io.github.eduardoconceicao90.desafio_mv.domain.cliente.enums.TipoCliente;
import io.github.eduardoconceicao90.desafio_mv.domain.conta.Conta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
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

    @Enumerated(EnumType.STRING)
    protected TipoCliente tipoCliente;

    @OneToMany(mappedBy = "cliente")
    protected List<Conta> contas = new ArrayList<>();

    @Embedded
    protected Endereco endereco;

}
