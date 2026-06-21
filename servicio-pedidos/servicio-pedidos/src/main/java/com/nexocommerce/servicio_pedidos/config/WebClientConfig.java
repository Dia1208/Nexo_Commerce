package com.nexocommerce.servicio_pedidos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/*
 * Configuración de WebClient.
 * Permite crear clientes HTTP para consumir otros microservicios.
 */
@Configuration
public class WebClientConfig {

    /*
     * Bean reutilizable de WebClient.Builder.
     * Se usa para construir clientes REST dentro del proyecto.
     */
    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}