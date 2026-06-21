package com.nexocommerce.servicio_pagos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * DTO utilizado para devolver respuestas de error simples.
 * Permite mostrar al cliente un mensaje claro y el estado HTTP correspondiente.
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