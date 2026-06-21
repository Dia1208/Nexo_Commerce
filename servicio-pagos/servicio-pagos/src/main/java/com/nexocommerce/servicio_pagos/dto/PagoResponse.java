package com.nexocommerce.servicio_pagos.dto;

import com.nexocommerce.servicio_pagos.entity.EstadoPago;
import com.nexocommerce.servicio_pagos.entity.MetodoPago;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/*
 * DTO utilizado para devolver la información de un pago al cliente.
 */
@Data
@AllArgsConstructor
public class PagoResponse {

    // Identificador del pago.
    private Long id;

    // Identificador del pedido pagado.
    private Long pedidoId;

    // Correo del usuario.
    private String correoUsuario;

    // Monto pagado.
    private BigDecimal monto;

    // Método de pago utilizado.
    private MetodoPago metodoPago;

    // Estado actual del pago.
    private EstadoPago estado;

    // Fecha de creación del pago.
    private LocalDateTime fechaPago;
}