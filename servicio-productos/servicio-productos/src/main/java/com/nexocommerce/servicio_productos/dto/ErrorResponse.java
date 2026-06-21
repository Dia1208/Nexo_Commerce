package com.nexocommerce.servicio_productos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * DTO utilizado para devolver respuestas de error de forma clara.
 * Se usa cuando ocurre una excepción o una validación falla.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    // Mensaje descriptivo del error.
    private String mensaje;

    // Código HTTP asociado al error.
    private Integer estado;
}