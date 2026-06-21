package com.nexocommerce.servicio_productos.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

/*
 * Esta clase representa un producto dentro del sistema.
 * Se almacena en la base de datos con información como
 * nombre, descripción, precio, stock y categoría.
 */
@Entity
@Table(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto {

    // Identificador único del producto.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre del producto.
    @Column(nullable = false)
    private String nombre;

    // Descripción del producto.
    private String descripcion;

    // Precio del producto.
    @Column(nullable = false)
    private BigDecimal precio;

    // Cantidad disponible en inventario.
    @Column(nullable = false)
    private Integer stock;

    // Categoría del producto.
    @Column(nullable = false)
    private String categoria;
}