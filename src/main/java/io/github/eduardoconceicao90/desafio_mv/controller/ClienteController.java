package io.github.eduardoconceicao90.desafio_mv.controller;

import io.github.eduardoconceicao90.desafio_mv.domain.cliente.PessoaFisica;
import io.github.eduardoconceicao90.desafio_mv.domain.cliente.PessoaJuridica;
import io.github.eduardoconceicao90.desafio_mv.domain.cliente.dto.ClienteDTO;
import io.github.eduardoconceicao90.desafio_mv.domain.cliente.dto.PessoaFisicaDTO;
import io.github.eduardoconceicao90.desafio_mv.domain.cliente.dto.PessoaJuridicaDTO;
import io.github.eduardoconceicao90.desafio_mv.service.ClienteService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clientes")
@SecurityRequirement(name = "bearer-key")
public class ClienteController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> buscarClientePorId(@PathVariable Long id) {
        return ResponseEntity.ok().body(mapper.map(clienteService.findById(id), ClienteDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> buscarClientes() {
        var clientes = clienteService.findAll().stream().map(x -> mapper.map(x, ClienteDTO.class)).collect(Collectors.toList());
        return ResponseEntity.ok().body(clientes);
    }

    //------------------------------------------ CLIENTE PESSOA FISICA:

    @PostMapping(value = "/cadastrarPessoaFisica")
    public ResponseEntity<PessoaFisica> cadastrarPessoaFisica(@Valid @RequestBody PessoaFisica pessoa) {
        var novaPessoa = clienteService.cadastrarPessoaFisica(pessoa);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novaPessoa.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/atualizarPessoaFisica/{id}")
    public ResponseEntity<PessoaFisicaDTO> atualizarPessoaFisica(@Valid @RequestBody PessoaFisicaDTO pessoa, @PathVariable Long id) {
        pessoa.setId(id);
        return ResponseEntity.ok().body(mapper.map(clienteService.atualizarPessoaFisica(pessoa, id), PessoaFisicaDTO.class));
    }

    //------------------------------------------ CLIENTE PESSOA JURIDICA:

    @PostMapping(value = "/cadastrarPessoaJuridica")
    public ResponseEntity<PessoaJuridica> cadastrarPessoaJuridica(@Valid @RequestBody PessoaJuridica pessoa) {
        var novaPessoa = clienteService.cadastrarPessoaJuridica(pessoa);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novaPessoa.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/atualizarPessoaJuridica/{id}")
    public ResponseEntity<PessoaJuridicaDTO> atualizarPessoaJuridica(@Valid @RequestBody PessoaJuridicaDTO pessoa, @PathVariable Long id) {
        pessoa.setId(id);
        return ResponseEntity.ok().body(mapper.map(clienteService.atualizarPessoaJuridica(pessoa, id), PessoaJuridicaDTO.class));
    }

    //------------------------------------------

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletarOuInativarCliente(@PathVariable Long id) {
        clienteService.deletarOuInativarCliente(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/ativarCliente/{id}")
    public void ativarCliente(@PathVariable Long id){
        clienteService.ativarCliente(id);
    }

}