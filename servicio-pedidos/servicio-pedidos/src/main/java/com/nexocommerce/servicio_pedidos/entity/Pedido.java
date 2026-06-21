package com.nexocommerce.servicio_pedidos.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/*
 * Esta clase representa un pedido realizado por un usuario.
 * Guarda la información básica del producto, la cantidad,
 * el total y el estado actual del pedido.
 */
@Entity
@Table(name = "pedidos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedido {

    // Identificador único del pedido.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Correo del usuario que realizó el pedido.
    @Column(nullable = false)
    private String correoUsuario;

    // Identificador del producto comprado.
    @Column(nullable = false)
    private Long productoId;

    // Nombre del producto comprado.
    @Column(nullable = false)
    private String nombreProducto;

    // Cantidad de unidades solicitadas.
    @Column(nullable = false)
    private Integer cantidad;

    // Precio unitario del producto.
    @Column(nullable = false)
    private BigDecimal precioUnitario;

    // Total calculado del pedido.
    @Column(nullable = false)
    private BigDecimal total;

    // Estado actual del pedido.
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoPedido estado;

    // Fecha en la que se creó el pedido.
    @Column(nullable = false)
    private LocalDateTime fechaCreacion;
}