package com.nexocommerce.servicio_reportes.dto;

import com.nexocommerce.servicio_reportes.entity.TipoReporte;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/*
 * DTO utilizado para devolver reportes al cliente.
 */
@Data
@AllArgsConstructor
public class ReporteResponse {

    // Identificador del reporte.
    private Long id;

    // Nombre del reporte.
    private String nombre;

    // Tipo del reporte.
    private TipoReporte tipo;

    // Cantidad de pedidos.
    private Integer cantidadPedidos;

    // Cantidad de productos.
    private Integer cantidadProductos;

    // Total de ventas.
    private BigDecimal totalVentas;

    // Fecha de generación.
    private LocalDateTime fechaGeneracion;
}