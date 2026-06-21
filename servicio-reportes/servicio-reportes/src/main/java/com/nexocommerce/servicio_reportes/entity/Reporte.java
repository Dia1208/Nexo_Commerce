package com.nexocommerce.servicio_reportes.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/*
 * Esta clase representa un reporte generado en el sistema.
 * Guarda información resumida para análisis.
 */
@Entity
@Table(name = "reportes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reporte {

    // Identificador único del reporte.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre del reporte.
    @Column(nullable = false)
    private String nombre;

    // Tipo del reporte.
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoReporte tipo;

    // Cantidad de pedidos registrados.
    @Column(nullable = false)
    private Integer cantidadPedidos;

    // Cantidad de productos vendidos.
    @Column(nullable = false)
    private Integer cantidadProductos;

    // Total de ventas.
    @Column(nullable = false)
    private BigDecimal totalVentas;

    // Fecha de generación del reporte.
    @Column(nullable = false)
    private LocalDateTime fechaGeneracion;
}