package com.nexocommerce.servicio_notificaciones.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * DTO utilizado para devolver respuestas de error simples.
 * Se usa cuando ocurre una excepción o una validación falla.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    // Mensaje descriptivo del error ocurrido.
    private String mensaje;

    // Código HTTP asociado al error.
    private Integer estado;
}