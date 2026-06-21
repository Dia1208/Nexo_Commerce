/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  io.swagger.v3.oas.models.OpenAPI
 *  io.swagger.v3.oas.models.info.Info
 *  org.springframework.context.annotation.Bean
 *  org.springframework.context.annotation.Configuration
 */
package com.nexocommerce.servicio_productos.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI productosOpenAPI() {
        return new OpenAPI().info(new Info().title("Servicio de Productos - NexoCommerce").version("1.0").description("API para gestionar productos, stock y categor\u00edas"));
    }
}
