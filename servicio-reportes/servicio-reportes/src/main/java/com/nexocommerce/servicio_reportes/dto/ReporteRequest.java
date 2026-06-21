package com.nexocommerce.servicio_reportes.dto;

import com.nexocommerce.servicio_reportes.entity.TipoReporte;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

/*
 * DTO utilizado para recibir los datos necesarios
 * al crear un reporte.
 */
@Data
public class ReporteRequest {

    // Nombre del reporte.
    @NotBlank(message = "El nombre del reporte es obligatorio")
    private String nombre;

    // Tipo del reporte.
    @NotNull(message = "El tipo de reporte es obligatorio")
    private TipoReporte tipo;

    // Cantidad de pedidos.
    @NotNull(message = "La cantidad de pedidos es obligatoria")
    @Min(value = 0, message = "La cantidad de pedidos no puede ser negativa")
    private Integer cantidadPedidos;

    // Cantidad de productos.
    @NotNull(message = "La cantidad de productos es obligatoria")
    @Min(value = 0, message = "La cantidad de productos no puede ser negativa")
    private Integer cantidadProductos;

    // Total de ventas.
    @NotNull(message = "El total de ventas es obligatorio")
    @DecimalMin(value = "0.0", message = "El total de ventas no puede ser negativo")
    private BigDecimal totalVentas;
}