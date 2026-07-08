package com.nexocommerce.servicio_checkout.dto;

import lombok.Data;

import java.math.BigDecimal;

/*
 * DTO utilizado para recibir los datos del producto
 * desde el microservicio servicio-productos.
 */
@Data
public class ProductoResponse {

    private Long id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private Integer stock;
    private String categoria;
}