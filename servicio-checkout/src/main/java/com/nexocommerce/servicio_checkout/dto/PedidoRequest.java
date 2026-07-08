package com.nexocommerce.servicio_checkout.dto;

import lombok.Data;

import java.math.BigDecimal;

/*
 * DTO que servicio-checkout envía hacia servicio-pedidos
 * para registrar un pedido ya calculado.
 */
@Data
public class PedidoRequest {

    private String correoUsuario;
    private Long productoId;
    private String nombreProducto;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal total;
}