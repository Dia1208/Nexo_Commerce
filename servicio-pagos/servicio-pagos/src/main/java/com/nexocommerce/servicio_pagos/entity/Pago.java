package com.nexocommerce.servicio_pagos.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/*
 * Esta clase representa un pago realizado dentro del sistema.
 * Cada pago queda asociado a un pedido mediante el pedidoId.
 */
@Entity
@Table(name = "pagos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pago {

    // Identificador único del pago.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Identificador del pedido relacionado con este pago.
    @Column(nullable = false)
    private Long pedidoId;

    // Correo del usuario que realiza el pago.
    @Column(nullable = false)
    private String correoUsuario;

    // Monto total pagado.
    @Column(nullable = false)
    private BigDecimal monto;

    // Método usado para pagar.
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MetodoPago metodoPago;

    // Estado actual del pago.
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoPago estado;

    // Fecha en que se creó el pago.
    @Column(nullable = false)
    private LocalDateTime fechaPago;
}