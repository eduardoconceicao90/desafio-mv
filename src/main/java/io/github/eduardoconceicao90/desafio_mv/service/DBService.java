package io.github.eduardoconceicao90.desafio_mv.service;

import io.github.eduardoconceicao90.desafio_mv.infra.security.user.UserSS;
import io.github.eduardoconceicao90.desafio_mv.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DBService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Bean
    public void instanciaDB() {

        UserSS user = new UserSS(null, "administrador", encoder.encode("123"));
        usuarioRepository.save(user);

    }

}