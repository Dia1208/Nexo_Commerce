package com.nexocommerce.servicio_pagos.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * Esta clase configura Swagger para documentar la API.
 * Permite visualizar y probar los endpoints del microservicio.
 */
@Configuration
public class SwaggerConfig {

    // Configura la información general de Swagger.
    @Bean
    public OpenAPI pagosOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Servicio de Pagos - NexoCommerce")
                        .version("1.0")
                        .description("API para gestionar pagos de pedidos"));
    }
}