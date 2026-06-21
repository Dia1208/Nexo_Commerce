package com.nexocommerce.servicio_pedidos.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * Configuración de Swagger/OpenAPI para el microservicio de pedidos.
 * Permite documentar y probar los endpoints desde Swagger UI.
 */
@Configuration
public class SwaggerConfig {

    // Define la información principal que se mostrará en Swagger.
    @Bean
    public OpenAPI pedidosOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Servicio de Pedidos - NexoCommerce")
                        .version("1.0")
                        .description("API para gestionar pedidos de clientes"));
    }
}