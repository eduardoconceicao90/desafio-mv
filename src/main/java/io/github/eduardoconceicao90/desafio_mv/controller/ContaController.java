package io.github.eduardoconceicao90.desafio_mv.controller;

import io.github.eduardoconceicao90.desafio_mv.service.ContaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/contas")
@SecurityRequirement(name = "bearer-key")
public class ContaController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ContaService contaService;

}
