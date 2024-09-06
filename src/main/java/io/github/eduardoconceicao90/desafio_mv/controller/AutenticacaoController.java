package io.github.eduardoconceicao90.desafio_mv.controller;

import io.github.eduardoconceicao90.desafio_mv.infra.security.DadosAutenticacao;
import io.github.eduardoconceicao90.desafio_mv.infra.security.jwt.DadosTokenJWT;
import io.github.eduardoconceicao90.desafio_mv.infra.security.jwt.JWTUtil;
import io.github.eduardoconceicao90.desafio_mv.infra.security.user.UserSS;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping
    public ResponseEntity<DadosTokenJWT> efetuarLogin(@Valid @RequestBody DadosAutenticacao dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = manager.authenticate(authenticationToken);
        var tokenJWT = jwtUtil.gerarToken((UserSS) authentication.getPrincipal());
        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }

}