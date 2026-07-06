package com.nexocommerce.servicio_pedidos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/*
 * Configuración de WebClient para permitir comunicación REST entre microservicios.
 */
@Configuration
public class WebClientConfig {

    /*
     * Bean utilizado para construir clientes HTTP.
     */
    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}