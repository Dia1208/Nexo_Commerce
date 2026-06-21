package com.nexocommerce.servicio_notificaciones.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/*
 * DTO utilizado para devolver errores controlados.
 */
@Data
@AllArgsConstructor
public class ErrorResponse {

    // Código del error.
    private String codigo;

    // Mensaje del error.
    private String mensaje;
}