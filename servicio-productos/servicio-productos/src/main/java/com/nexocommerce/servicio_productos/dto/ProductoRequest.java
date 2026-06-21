/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  jakarta.validation.constraints.DecimalMin
 *  jakarta.validation.constraints.Min
 *  jakarta.validation.constraints.NotBlank
 *  jakarta.validation.constraints.NotNull
 *  lombok.Generated
 */
package com.nexocommerce.servicio_productos.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Generated;

public class ProductoRequest {
    @NotBlank(message="El nombre del producto es obligatorio")
    private @NotBlank(message="El nombre del producto es obligatorio") String nombre;
    private String descripcion;
    @NotNull(message="El precio es obligatorio")
    @DecimalMin(value="1.0", message="El precio debe ser mayor a 0")
    private @NotNull(message="El precio es obligatorio") @DecimalMin(value="1.0", message="El precio debe ser mayor a 0") BigDecimal precio;
    @NotNull(message="El stock es obligatorio")
    @Min(value=0L, message="El stock no puede ser negativo")
    private @NotNull(message="El stock es obligatorio") @Min(value=0L, message="El stock no puede ser negativo") Integer stock;
    @NotBlank(message="La categor\u00eda es obligatoria")
    private @NotBlank(message="La categor\u00eda es obligatoria") String categoria;

    @Generated
    public ProductoRequest() {
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
        if (!(o instanceof ProductoRequest)) {
            return false;
        }
        ProductoRequest other = (ProductoRequest)o;
        if (!other.canEqual(this)) {
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
        return other instanceof ProductoRequest;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
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
        return "ProductoRequest(nombre=" + this.getNombre() + ", descripcion=" + this.getDescripcion() + ", precio=" + String.valueOf(this.getPrecio()) + ", stock=" + this.getStock() + ", categoria=" + this.getCategoria() + ")";
    }
}
