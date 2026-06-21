package com.nexocommerce.servicio_reportes.dto;

import com.nexocommerce.servicio_reportes.entity.TipoReporte;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/*
 * DTO utilizado para devolver la información de un reporte
 * como respuesta al cliente.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReporteResponse {

    private Long id;
    private String nombre;
    private TipoReporte tipo;
    private Integer cantidadPedidos;
    private Integer cantidadProductos;
    private BigDecimal totalVentas;
    private LocalDateTime fechaGeneracion;
}