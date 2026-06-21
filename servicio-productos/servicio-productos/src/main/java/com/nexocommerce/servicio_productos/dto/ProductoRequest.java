package com.nexocommerce.servicio_productos.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

/*
 * DTO utilizado para recibir los datos necesarios
 * al crear o actualizar un producto.
 */
@Data
public class ProductoRequest {

    // Nombre del producto.
    @NotBlank(message = "El nombre del producto es obligatorio")
    private String nombre;

    // Descripción del producto.
    private String descripcion;

    // Precio del producto.
    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "1.0", message = "El precio debe ser mayor a 0")
    private BigDecimal precio;

    // Cantidad disponible en inventario.
    @NotNull(message = "El stock es obligatorio")
    @Min(value = 0, message = "El stock no puede ser negativo")
    private Integer stock;

    // Categoría del producto.
    @NotBlank(message = "La categoría es obligatoria")
    private String categoria;
}