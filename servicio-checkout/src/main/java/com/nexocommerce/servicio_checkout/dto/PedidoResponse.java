package com.nexocommerce.servicio_checkout.dto;

import lombok.Data;

import java.math.BigDecimal;

/*
 * DTO utilizado para recibir la respuesta desde servicio-pedidos.
 */
@Data
public class PedidoResponse {

    private Long id;
    private String correoUsuario;
    private Long productoId;
    private String nombreProducto;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal total;
    private String estado;
}