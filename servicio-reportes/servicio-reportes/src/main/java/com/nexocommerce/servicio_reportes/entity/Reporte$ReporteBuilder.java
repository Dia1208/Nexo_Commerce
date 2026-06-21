/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package com.nexocommerce.servicio_reportes.entity;

import com.nexocommerce.servicio_reportes.entity.Reporte;
import com.nexocommerce.servicio_reportes.entity.TipoReporte;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Generated;

@Generated
public static class Reporte.ReporteBuilder {
    @Generated
    private Long id;
    @Generated
    private String nombre;
    @Generated
    private TipoReporte tipo;
    @Generated
    private Integer cantidadPedidos;
    @Generated
    private Integer cantidadProductos;
    @Generated
    private BigDecimal totalVentas;
    @Generated
    private LocalDateTime fechaGeneracion;

    @Generated
    Reporte.ReporteBuilder() {
    }

    @Generated
    public Reporte.ReporteBuilder id(Long id) {
        this.id = id;
        return this;
    }

    @Generated
    public Reporte.ReporteBuilder nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    @Generated
    public Reporte.ReporteBuilder tipo(TipoReporte tipo) {
        this.tipo = tipo;
        return this;
    }

    @Generated
    public Reporte.ReporteBuilder cantidadPedidos(Integer cantidadPedidos) {
        this.cantidadPedidos = cantidadPedidos;
        return this;
    }

    @Generated
    public Reporte.ReporteBuilder cantidadProductos(Integer cantidadProductos) {
        this.cantidadProductos = cantidadProductos;
        return this;
    }

    @Generated
    public Reporte.ReporteBuilder totalVentas(BigDecimal totalVentas) {
        this.totalVentas = totalVentas;
        return this;
    }

    @Generated
    public Reporte.ReporteBuilder fechaGeneracion(LocalDateTime fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
        return this;
    }

    @Generated
    public Reporte build() {
        return new Reporte(this.id, this.nombre, this.tipo, this.cantidadPedidos, this.cantidadProductos, this.totalVentas, this.fechaGeneracion);
    }

    @Generated
    public String toString() {
        return "Reporte.ReporteBuilder(id=" + this.id + ", nombre=" + this.nombre + ", tipo=" + String.valueOf((Object)this.tipo) + ", cantidadPedidos=" + this.cantidadPedidos + ", cantidadProductos=" + this.cantidadProductos + ", totalVentas=" + String.valueOf(this.totalVentas) + ", fechaGeneracion=" + String.valueOf(this.fechaGeneracion) + ")";
    }
}
