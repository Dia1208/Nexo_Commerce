/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  jakarta.persistence.Column
 *  jakarta.persistence.Entity
 *  jakarta.persistence.GeneratedValue
 *  jakarta.persistence.GenerationType
 *  jakarta.persistence.Id
 *  jakarta.persistence.Table
 *  lombok.Generated
 */
package com.nexocommerce.servicio_productos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.Generated;

@Entity
@Table(name="productos")
public class Producto {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false)
    private String nombre;
    private String descripcion;
    @Column(nullable=false)
    private BigDecimal precio;
    @Column(nullable=false)
    private Integer stock;
    @Column(nullable=false)
    private String categoria;

    @Generated
    public static ProductoBuilder builder() {
        return new ProductoBuilder();
    }

    @Generated
    public Long getId() {
        return this.id;
    }

    @Generated
    public String getNombre() {
        return this.nombre;
    }

    @Generated
    public String getDescripcion() {
        return this.descripcion;
    }

    @Generated
    public BigDecimal getPrecio() {
        return this.precio;
    }

    @Generated
    public Integer getStock() {
        return this.stock;
    }

    @Generated
    public String getCategoria() {
        return this.categoria;
    }

    @Generated
    public void setId(Long id) {
        this.id = id;
    }

    @Generated
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Generated
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Generated
    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    @Generated
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    @Generated
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Producto)) {
            return false;
        }
        Producto other = (Producto)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Long this$id = this.getId();
        Long other$id = other.getId();
        if (this$id == null ? other$id != null : !((Object)this$id).equals(other$id)) {
            return false;
        }
        Integer this$stock = this.getStock();
        Integer other$stock = other.getStock();
        if (this$stock == null ? other$stock != null : !((Object)this$stock).equals(other$stock)) {
            return false;
        }
        String this$nombre = this.getNombre();
        String other$nombre = other.getNombre();
        if (this$nombre == null ? other$nombre != null : !this$nombre.equals(other$nombre)) {
            return false;
        }
        String this$descripcion = this.getDescripcion();
        String other$descripcion = other.getDescripcion();
        if (this$descripcion == null ? other$descripcion != null : !this$descripcion.equals(other$descripcion)) {
            return false;
        }
        BigDecimal this$precio = this.getPrecio();
        BigDecimal other$precio = other.getPrecio();
        if (this$precio == null ? other$precio != null : !((Object)this$precio).equals(other$precio)) {
            return false;
        }
        String this$categoria = this.getCategoria();
        String other$categoria = other.getCategoria();
        return !(this$categoria == null ? other$categoria != null : !this$categoria.equals(other$categoria));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof Producto;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        Integer $stock = this.getStock();
        result = result * 59 + ($stock == null ? 43 : ((Object)$stock).hashCode());
        String $nombre = this.getNombre();
        result = result * 59 + ($nombre == null ? 43 : $nombre.hashCode());
        String $descripcion = this.getDescripcion();
        result = result * 59 + ($descripcion == null ? 43 : $descripcion.hashCode());
        BigDecimal $precio = this.getPrecio();
        result = result * 59 + ($precio == null ? 43 : ((Object)$precio).hashCode());
        String $categoria = this.getCategoria();
        result = result * 59 + ($categoria == null ? 43 : $categoria.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "Producto(id=" + this.getId() + ", nombre=" + this.getNombre() + ", descripcion=" + this.getDescripcion() + ", precio=" + String.valueOf(this.getPrecio()) + ", stock=" + this.getStock() + ", categoria=" + this.getCategoria() + ")";
    }

    @Generated
    public Producto() {
    }

    @Generated
    public Producto(Long id, String nombre, String descripcion, BigDecimal precio, Integer stock, String categoria) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
    }

    @Generated
    public static class ProductoBuilder {
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
        ProductoBuilder() {
        }

        @Generated
        public ProductoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        @Generated
        public ProductoBuilder nombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        @Generated
        public ProductoBuilder descripcion(String descripcion) {
            this.descripcion = descripcion;
            return this;
        }

        @Generated
        public ProductoBuilder precio(BigDecimal precio) {
            this.precio = precio;
            return this;
        }

        @Generated
        public ProductoBuilder stock(Integer stock) {
            this.stock = stock;
            return this;
        }

        @Generated
        public ProductoBuilder categoria(String categoria) {
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
}
