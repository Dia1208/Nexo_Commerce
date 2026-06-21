package com.nexocommerce.servicio_usuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * Clase principal del microservicio de usuarios.
 * Desde aquí se inicia la aplicación Spring Boot.
 */
@SpringBootApplication
public class ServicioUsuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioUsuariosApplication.class, args);
	}
}