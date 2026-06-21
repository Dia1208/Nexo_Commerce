package com.nexocommerce.servicio_pagos.dto;

import com.nexocommerce.servicio_pagos.entity.MetodoPago;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

/*
 * DTO utilizado para recibir los datos necesarios
 * al registrar un pago.
 */
@Data
public class PagoRequest {

    // Id del pedido que se va a pagar.
    @NotNull(message = "El id del pedido es obligatorio")
    private Long pedidoId;

    // Correo del usuario que realiza el pago.
    @NotBlank(message = "El correo del usuario es obligatorio")
    private String correoUsuario;

    // Monto total del pago.
    @NotNull(message = "El monto es obligatorio")
    @DecimalMin(value = "1.0", message = "El monto debe ser mayor a 0")
    private BigDecimal monto;

    // Método de pago seleccionado.
    @NotNull(message = "El método de pago es obligatorio")
    private MetodoPago metodoPago;
}