package io.github.eduardoconceicao90.desafio_mv.service;

import io.github.eduardoconceicao90.desafio_mv.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

}
