package io.github.eduardoconceicao90.desafio_mv.infra.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API XPTO")
                        .description("API para efetuar cadastro de clientes e realizar movimentações bancárias")
                        .version("1.0"));
    }

}
