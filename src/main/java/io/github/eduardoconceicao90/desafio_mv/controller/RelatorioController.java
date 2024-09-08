package io.github.eduardoconceicao90.desafio_mv.controller;

import io.github.eduardoconceicao90.desafio_mv.domain.relatorio.SaldoCliente;
import io.github.eduardoconceicao90.desafio_mv.infra.exception.ApiException;
import io.github.eduardoconceicao90.desafio_mv.service.RelatorioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/relatorios")
@SecurityRequirement(name = "bearer-key")
public class RelatorioController {

    @Autowired
    private RelatorioService relatorioService;

    @Operation(summary = "END-POINT para verificar o saldo do cliente pessoa física ou jurídica.")
    @GetMapping(value = "/saldoCliente/{id}")
    public ResponseEntity<SaldoCliente> getSaldoCliente(@PathVariable Long id) throws ApiException {
        SaldoCliente saldoCliente = relatorioService.getSaldoCliente(id);
        return ResponseEntity.ok().body(saldoCliente);
    }
}
