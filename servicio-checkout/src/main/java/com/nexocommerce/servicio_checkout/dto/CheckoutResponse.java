package com.nexocommerce.servicio_checkout.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/*
 * DTO de respuesta final del proceso de checkout.
 */
@Data
@Builder
public class CheckoutResponse {

    private String mensaje;
    private Long pedidoId;
    private String correoUsuario;
    private Long productoId;
    private String nombreProducto;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal total;
    private String estado;
}