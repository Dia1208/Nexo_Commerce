/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  io.jsonwebtoken.Jwts
 *  io.jsonwebtoken.security.Keys
 *  org.springframework.beans.factory.annotation.Value
 *  org.springframework.cloud.gateway.filter.GatewayFilterChain
 *  org.springframework.cloud.gateway.filter.GlobalFilter
 *  org.springframework.core.Ordered
 *  org.springframework.http.HttpStatus
 *  org.springframework.http.HttpStatusCode
 *  org.springframework.stereotype.Component
 *  org.springframework.web.server.ServerWebExchange
 *  reactor.core.publisher.Mono
 */
package com.nexocommerce.servicio_gateway.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.List;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthenticationFilter
implements GlobalFilter,
Ordered {
    @Value(value="${jwt.secret}")
    private String jwtSecret;
    private static final List<String> RUTAS_PUBLICAS = List.of("/api/auth/login", "/api/auth/register", "/api/auth/test", "/api/gateway/health", "/actuator/health");

    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        System.out.println("Ruta recibida en Gateway: " + path);
        if (RUTAS_PUBLICAS.contains(path)) {
            System.out.println("Ruta p\u00fablica, no requiere token");
            return chain.filter(exchange);
        }
        String authorizationHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
        System.out.println("Header Authorization: " + authorizationHeader);
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            System.out.println("No lleg\u00f3 token o no empieza con Bearer");
            exchange.getResponse().setStatusCode((HttpStatusCode)HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        String token = authorizationHeader.substring(7).trim();
        try {
            SecretKey key = Keys.hmacShaKeyFor((byte[])this.jwtSecret.getBytes(StandardCharsets.UTF_8));
            System.out.println("Validando token en Gateway...");
            System.out.println("Largo de jwt.secret: " + this.jwtSecret.length());
            Jwts.parser().verifyWith(key).build().parseSignedClaims((CharSequence)token);
            System.out.println("Token v\u00e1lido. Continuando hacia microservicio.");
            return chain.filter(exchange);
        }
        catch (Exception ex) {
            System.out.println("Error validando JWT en Gateway: " + ex.getMessage());
            exchange.getResponse().setStatusCode((HttpStatusCode)HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
    }

    public int getOrder() {
        return -1;
    }
}
