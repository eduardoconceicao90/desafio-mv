package io.github.eduardoconceicao90.desafio_mv.controller;

import io.github.eduardoconceicao90.desafio_mv.domain.conta.pessoaFisica.ContaPF;
import io.github.eduardoconceicao90.desafio_mv.domain.conta.pessoaJuridica.ContaPJ;
import io.github.eduardoconceicao90.desafio_mv.infra.exception.ApiException;
import io.github.eduardoconceicao90.desafio_mv.service.ContaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/contas")
@SecurityRequirement(name = "bearer-key")
public class ContaController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ContaService contaService;

    //------------------------------------------ CONTA PESSOA FISICA:

    @GetMapping("/buscarContaPFPorId/{id}")
    public ResponseEntity<ContaPF> buscarContaPFPorId(@PathVariable Long id) {
        return ResponseEntity.ok().body(mapper.map(contaService.findContaPFById(id), ContaPF.class));
    }

    @PostMapping(value = "/cadastrarContaPF")
    public ResponseEntity<ContaPF> cadastrarContaPF(@Valid @RequestBody ContaPF conta) throws ApiException {
        var novaConta = contaService.cadastrarContaPF(conta);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novaConta.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/inativarContaPF/{id}")
    public void inativarContaPF(@PathVariable Long id){
        contaService.inativarContaPF(id);
    }

    @PutMapping(value = "/ativarContaPF/{id}")
    public void ativarContaPF(@PathVariable Long id){
        contaService.ativarContaPF(id);
    }

    //------------------------------------------ CONTA PESSOA JURIDICA:

    @GetMapping("/buscarContaPJPorId/{id}")
    public ResponseEntity<ContaPJ> buscarContaPJPorId(@PathVariable Long id) {
        return ResponseEntity.ok().body(mapper.map(contaService.findContaPJById(id), ContaPJ.class));
    }

    @PostMapping(value = "/cadastrarContaPJ")
    public ResponseEntity<ContaPJ> cadastrarContaPJ(@Valid @RequestBody ContaPJ conta) throws ApiException {
        var novaConta = contaService.cadastrarContaPJ(conta);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novaConta.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/inativarContaPJ/{id}")
    public void inativarContaPJ(@PathVariable Long id){
        contaService.inativarContaPJ(id);
    }

    @PutMapping(value = "/ativarContaPJ/{id}")
    public void ativarContaPJ(@PathVariable Long id){
        contaService.ativarContaPJ(id);
    }
}
