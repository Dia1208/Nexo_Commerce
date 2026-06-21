/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  org.springframework.context.annotation.Bean
 *  org.springframework.context.annotation.Configuration
 *  org.springframework.security.config.web.server.ServerHttpSecurity
 *  org.springframework.security.config.web.server.ServerHttpSecurity$AuthorizeExchangeSpec$Access
 *  org.springframework.security.config.web.server.ServerHttpSecurity$CsrfSpec
 *  org.springframework.security.web.server.SecurityWebFilterChain
 */
package com.nexocommerce.servicio_gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http.csrf(ServerHttpSecurity.CsrfSpec::disable).authorizeExchange(exchange -> ((ServerHttpSecurity.AuthorizeExchangeSpec.Access)((ServerHttpSecurity.AuthorizeExchangeSpec.Access)((ServerHttpSecurity.AuthorizeExchangeSpec.Access)exchange.pathMatchers(new String[]{"/api/auth/**"})).permitAll().pathMatchers(new String[]{"/api/gateway/**"})).permitAll().pathMatchers(new String[]{"/actuator/health"})).permitAll().anyExchange().permitAll()).build();
    }
}
