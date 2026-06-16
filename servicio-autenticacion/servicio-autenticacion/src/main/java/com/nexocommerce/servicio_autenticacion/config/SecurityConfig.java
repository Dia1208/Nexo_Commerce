//Este archivo configura Spring Security.
package com.nexocommerce.servicio_autenticacion.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // Le dice a Spring que esta clase contiene configuraciones clave del sistema
@EnableWebSecurity // Activa la seguridad web de Spring Security en toda la aplicación
public class SecurityConfig {

    @Bean// Registra el filtro de seguridad en el contenedor de Spring para que proteja las rutas HTTP
    // Configura la seguridad HTTP: desactiva CSRF y permite el acceso público temporal a todas las rutas, incluyendo auth, Swagger y salud de la app
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/auth/**",
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/actuator/health"
                        ).permitAll()
                        .anyRequest().permitAll()
                )
                .build();
    }
    // Configura BCrypt como el algoritmo global para encriptar contraseñas mediante un hash seguro e irreversible.
    //Algorimo que proteje contraseñas
    @Bean
    public PasswordEncoder passwordEncoder() {//sirve para encriptar la contraseña antes de guardarla en MySQL.
        return new BCryptPasswordEncoder();
    }
}