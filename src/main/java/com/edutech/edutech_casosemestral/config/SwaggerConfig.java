package com.edutech.edutech_casosemestral.config;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                .title("Edutech Caso Semestral ")
                .version("1.0")
                .description("Documentacion de la API de Edutech Innovators SPA"));
    }
}
