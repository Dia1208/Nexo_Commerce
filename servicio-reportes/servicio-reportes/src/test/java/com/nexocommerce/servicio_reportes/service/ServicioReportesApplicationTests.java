package com.nexocommerce.servicio_reportes.service;

import com.nexocommerce.servicio_reportes.ServicioReportesApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/*
 * Prueba básica de arranque del microservicio de reportes.
 * Verifica que el contexto de Spring Boot cargue correctamente.
 */
@SpringBootTest(classes = ServicioReportesApplication.class)
class ServicioReportesApplicationTests {

    @Test
    void contextLoads() {
        // Si el contexto de Spring carga sin errores, la prueba pasa.
    }
}