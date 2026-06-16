package com.nexocommerce.servicio_autenticacion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {

    private String token;
    private String tipo;
    private String mensaje;
}