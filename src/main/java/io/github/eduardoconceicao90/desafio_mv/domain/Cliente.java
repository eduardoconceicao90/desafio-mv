package io.github.eduardoconceicao90.desafio_mv.domain;

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

    protected String tipoPessoa;

    @OneToMany(mappedBy = "cliente", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Conta> contas = new ArrayList<>();

    @Embedded
    private Endereco endereco;

    @OneToMany(mappedBy = "cliente", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Telefone> telefones = new ArrayList<>();

}
