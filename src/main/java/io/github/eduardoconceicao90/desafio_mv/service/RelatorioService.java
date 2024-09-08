package io.github.eduardoconceicao90.desafio_mv.service;

import io.github.eduardoconceicao90.desafio_mv.dao.RelatorioDAO;
import io.github.eduardoconceicao90.desafio_mv.domain.relatorio.SaldoCliente;
import io.github.eduardoconceicao90.desafio_mv.infra.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelatorioService {

    @Autowired
    private RelatorioDAO relatorioDAO;

    public SaldoCliente getSaldoCliente(Long id) throws ApiException {
        try{
            return relatorioDAO.getSaldoCliente(id);
        }catch (Exception e){
            e.printStackTrace();
            throw new ApiException("Nenhum resultado encontrado para o ID: " + id);
        }
    }



}
