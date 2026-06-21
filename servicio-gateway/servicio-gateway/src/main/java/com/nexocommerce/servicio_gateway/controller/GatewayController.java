package com.nexocommerce.servicio_gateway.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * Controlador básico del Gateway.
 * Permite verificar que el microservicio gateway está funcionando correctamente.
 */
@RestController
@RequestMapping(value = "/api/gateway")
public class GatewayController {

    // Endpoint de prueba para comprobar que el Gateway está activo.
    @GetMapping(value = "/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Servicio Gateway funcionando correctamente");
    }
}