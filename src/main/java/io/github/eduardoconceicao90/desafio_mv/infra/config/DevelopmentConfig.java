package io.github.eduardoconceicao90.desafio_mv.infra.config;

import io.github.eduardoconceicao90.desafio_mv.service.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("development")
public class DevelopmentConfig {

    @Autowired
    private DBService dbService;

    @Bean
    public boolean instanciaDB() {
        this.dbService.instanciaDB();
        return true;
    }
}
