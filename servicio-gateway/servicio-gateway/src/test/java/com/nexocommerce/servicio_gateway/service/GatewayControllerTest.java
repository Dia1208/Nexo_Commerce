package com.nexocommerce.servicio_gateway.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Prueba unitaria básica del controlador del Gateway.
 * Verifica que el endpoint de salud responda correctamente.
 */
class GatewayControllerTest {

    @Test
    void healthDebeResponderCorrectamente() {
        GatewayController controller = new GatewayController();

        ResponseEntity<String> response = controller.health();

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Servicio Gateway funcionando correctamente", response.getBody());
    }
}