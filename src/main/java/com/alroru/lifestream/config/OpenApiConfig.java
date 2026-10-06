package com.alroru.lifestream.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI lifestreamOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Lifestream API")
                        .description("API de consulta del mundo animal con estilo Pokédex pero con animales reales.")
                        .version("0.0.1"));
    }
}
