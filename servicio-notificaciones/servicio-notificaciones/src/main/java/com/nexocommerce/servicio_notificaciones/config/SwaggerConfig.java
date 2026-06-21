package com.nexocommerce.servicio_notificaciones.config;

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
    public OpenAPI notificacionesOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Servicio de Notificaciones - NexoCommerce")
                        .version("1.0")
                        .description("API para gestionar notificaciones del sistema"));
    }
}