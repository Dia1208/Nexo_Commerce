package com.nexocommerce.servicio_gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/*
 * Configuración de seguridad del Gateway.
 * Desactiva CSRF porque se trabaja con una API REST
 * y permite que el filtro JWT personalizado controle
 * el acceso a las rutas protegidas.
 */
@Configuration
public class SecurityConfig {

    /*
     * Configura la cadena de seguridad para Spring WebFlux.
     * Las rutas públicas se permiten sin autenticación.
     * Las demás rutas pasan por el filtro JWT del Gateway.
     */
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchange -> exchange
                        .pathMatchers("/api/auth/**").permitAll()
                        .pathMatchers("/api/gateway/**").permitAll()
                        .pathMatchers("/actuator/health").permitAll()
                        .anyExchange().permitAll()
                )
                .build();
    }
}