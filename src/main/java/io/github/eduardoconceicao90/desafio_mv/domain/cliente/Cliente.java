package io.github.eduardoconceicao90.desafio_mv.domain.cliente;

import io.github.eduardoconceicao90.desafio_mv.domain.conta.Conta;
import io.github.eduardoconceicao90.desafio_mv.domain.cliente.enums.TipoCliente;
import jakarta.persistence.*;
import lombok.*;

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

    protected String nome;

    @Enumerated(EnumType.STRING)
    protected TipoCliente tipoCliente;

    @OneToMany(mappedBy = "cliente", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    protected List<Conta> contas = new ArrayList<>();

    @Embedded
    protected Endereco endereco;

    @OneToMany(mappedBy = "cliente", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    protected List<Telefone> telefones = new ArrayList<>();

}
