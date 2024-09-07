package io.github.eduardoconceicao90.desafio_mv.infra.config;

import io.github.eduardoconceicao90.desafio_mv.service.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("production")
public class ProductionConfig {

    @Autowired
    private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddl;

    @Bean
    public boolean instanciaBD() {
        if (ddl.equals("create")) {
            this.dbService.instanciaDB();
            return true;
        }
        return false;
    }

}
