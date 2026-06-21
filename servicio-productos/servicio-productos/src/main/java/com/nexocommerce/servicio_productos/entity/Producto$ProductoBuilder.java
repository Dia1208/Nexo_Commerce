/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package com.nexocommerce.servicio_productos.entity;

import com.nexocommerce.servicio_productos.entity.Producto;
import java.math.BigDecimal;
import lombok.Generated;

@Generated
public static class Producto.ProductoBuilder {
    @Generated
    private Long id;
    @Generated
    private String nombre;
    @Generated
    private String descripcion;
    @Generated
    private BigDecimal precio;
    @Generated
    private Integer stock;
    @Generated
    private String categoria;

    @Generated
    Producto.ProductoBuilder() {
    }

    @Generated
    public Producto.ProductoBuilder id(Long id) {
        this.id = id;
        return this;
    }

    @Generated
    public Producto.ProductoBuilder nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    @Generated
    public Producto.ProductoBuilder descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    @Generated
    public Producto.ProductoBuilder precio(BigDecimal precio) {
        this.precio = precio;
        return this;
    }

    @Generated
    public Producto.ProductoBuilder stock(Integer stock) {
        this.stock = stock;
        return this;
    }

    @Generated
    public Producto.ProductoBuilder categoria(String categoria) {
        this.categoria = categoria;
        return this;
    }

    @Generated
    public Producto build() {
        return new Producto(this.id, this.nombre, this.descripcion, this.precio, this.stock, this.categoria);
    }

    @Generated
    public String toString() {
        return "Producto.ProductoBuilder(id=" + this.id + ", nombre=" + this.nombre + ", descripcion=" + this.descripcion + ", precio=" + String.valueOf(this.precio) + ", stock=" + this.stock + ", categoria=" + this.categoria + ")";
    }
}
