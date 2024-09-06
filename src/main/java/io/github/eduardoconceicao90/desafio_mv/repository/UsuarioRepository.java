package io.github.eduardoconceicao90.desafio_mv.repository;

import io.github.eduardoconceicao90.desafio_mv.infra.security.user.UserSS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UserSS, Long> {

    UserDetails findByLogin(String login);

}
