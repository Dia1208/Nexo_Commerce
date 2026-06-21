package com.nexocommerce.servicio_gateway.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.List;

/*
 * Filtro global del Gateway encargado de validar el token JWT.
 * Este filtro se ejecuta antes de redirigir la petición hacia los microservicios.
 */
@Component
public class JwtAuthenticationFilter implements GlobalFilter, Ordered {

    /*
     * Clave secreta usada para validar los tokens JWT.
     * Debe ser la misma clave utilizada en el microservicio de autenticación.
     */
    @Value("${jwt.secret}")
    private String jwtSecret;

    /*
     * Lista de rutas públicas que no requieren token.
     * Estas rutas se pueden consumir sin enviar Authorization Bearer.
     */
    private static final List<String> RUTAS_PUBLICAS = List.of(
            "/api/auth/login",
            "/api/auth/register",
            "/api/auth/test",
            "/api/gateway/health",
            "/actuator/health"
    );

    /*
     * Método principal del filtro.
     * Verifica si la ruta es pública. Si no lo es,
     * valida que exista un token JWT correcto.
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();

        // Si la ruta es pública, se permite continuar sin token.
        if (RUTAS_PUBLICAS.contains(path)) {
            return chain.filter(exchange);
        }

        // Obtiene el header Authorization enviado por el cliente.
        String authorizationHeader = exchange.getRequest()
                .getHeaders()
                .getFirst("Authorization");

        /*
         * Si no existe el header Authorization o no empieza con Bearer,
         * se rechaza la petición con código 401 Unauthorized.
         */
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        // Extrae el token quitando la palabra "Bearer ".
        String token = authorizationHeader.substring(7).trim();

        try {
            /*
             * Crea la clave secreta a partir del texto configurado
             * en application.properties.
             */
            SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));

            /*
             * Valida la firma y estructura del token.
             * Si el token es inválido, vencido o fue alterado,
             * se lanzará una excepción.
             */
            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);

            // Si el token es válido, permite continuar hacia el microservicio.
            return chain.filter(exchange);

        } catch (Exception ex) {
            /*
             * Si ocurre cualquier error validando el token,
             * se responde con 401 Unauthorized.
             */
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
    }

    /*
     * Define el orden del filtro.
     * Un valor negativo hace que se ejecute antes que otros filtros.
     */
    @Override
    public int getOrder() {
        return -1;
    }
}