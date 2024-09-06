package io.github.eduardoconceicao90.desafio_mv.infra.security.user;

import io.github.eduardoconceicao90.desafio_mv.infra.exception.ApiException;
import io.github.eduardoconceicao90.desafio_mv.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String login) {
        UserDetails user = usuarioRepository.findByLogin(login);

        if(user != null){
            return user;
        }

        try {
            throw new ApiException("Login e/ou senha inválido(s)");
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }

    }

}
