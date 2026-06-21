package com.nexocommerce.servicio_usuarios.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * DTO utilizado para devolver respuestas de error simples.
 * Permite enviar al cliente un mensaje claro y el código HTTP correspondiente.
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
