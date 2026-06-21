
package com.nexocommerce.servicio_reportes.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI reportesOpenAPI() {
        return new OpenAPI().info(new Info().title("Servicio de Reportes - NexoCommerce").version("1.0").description("API para gestionar reportes del sistema"));
    }
}