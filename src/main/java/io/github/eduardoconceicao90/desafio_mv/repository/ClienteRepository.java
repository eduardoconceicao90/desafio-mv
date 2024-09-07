package io.github.eduardoconceicao90.desafio_mv.repository;

import io.github.eduardoconceicao90.desafio_mv.domain.cliente.Cliente;
import io.github.eduardoconceicao90.desafio_mv.domain.cliente.PessoaFisica;
import io.github.eduardoconceicao90.desafio_mv.domain.cliente.PessoaJuridica;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {

    @Query("SELECT p FROM PessoaFisica p WHERE p.cpf =:cpf")
    Optional<PessoaFisica> findByCpf(String cpf);

    @Query("SELECT p FROM PessoaJuridica p WHERE p.cnpj =:cnpj")
    Optional<PessoaJuridica> findByCnpj(String cnpj);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Cliente SET status_cliente = 'INATIVO' where id = ?1", nativeQuery = true)
    void inativarCliente(Long id);

}
