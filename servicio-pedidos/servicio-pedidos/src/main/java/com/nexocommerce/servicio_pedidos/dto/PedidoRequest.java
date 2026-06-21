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
package com.nexocommerce.servicio_pedidos.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Generated;

public class PedidoRequest {
    @NotBlank(message="El correo del usuario es obligatorio")
    private @NotBlank(message="El correo del usuario es obligatorio") String correoUsuario;
    @NotNull(message="El id del producto es obligatorio")
    private @NotNull(message="El id del producto es obligatorio") Long productoId;
    @NotBlank(message="El nombre del producto es obligatorio")
    private @NotBlank(message="El nombre del producto es obligatorio") String nombreProducto;
    @NotNull(message="La cantidad es obligatoria")
    @Min(value=1L, message="La cantidad debe ser mayor a 0")
    private @NotNull(message="La cantidad es obligatoria") @Min(value=1L, message="La cantidad debe ser mayor a 0") Integer cantidad;
    @NotNull(message="El precio unitario es obligatorio")
    @DecimalMin(value="1.0", message="El precio unitario debe ser mayor a 0")
    private @NotNull(message="El precio unitario es obligatorio") @DecimalMin(value="1.0", message="El precio unitario debe ser mayor a 0") BigDecimal precioUnitario;

    @Generated
    public PedidoRequest() {
    }

    @Generated
    public String getCorreoUsuario() {
        return this.correoUsuario;
    }

    @Generated
    public Long getProductoId() {
        return this.productoId;
    }

    @Generated
    public String getNombreProducto() {
        return this.nombreProducto;
    }

    @Generated
    public Integer getCantidad() {
        return this.cantidad;
    }

    @Generated
    public BigDecimal getPrecioUnitario() {
        return this.precioUnitario;
    }

    @Generated
    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    @Generated
    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    @Generated
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    @Generated
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @Generated
    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof PedidoRequest)) {
            return false;
        }
        PedidoRequest other = (PedidoRequest)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Long this$productoId = this.getProductoId();
        Long other$productoId = other.getProductoId();
        if (this$productoId == null ? other$productoId != null : !((Object)this$productoId).equals(other$productoId)) {
            return false;
        }
        Integer this$cantidad = this.getCantidad();
        Integer other$cantidad = other.getCantidad();
        if (this$cantidad == null ? other$cantidad != null : !((Object)this$cantidad).equals(other$cantidad)) {
            return false;
        }
        String this$correoUsuario = this.getCorreoUsuario();
        String other$correoUsuario = other.getCorreoUsuario();
        if (this$correoUsuario == null ? other$correoUsuario != null : !this$correoUsuario.equals(other$correoUsuario)) {
            return false;
        }
        String this$nombreProducto = this.getNombreProducto();
        String other$nombreProducto = other.getNombreProducto();
        if (this$nombreProducto == null ? other$nombreProducto != null : !this$nombreProducto.equals(other$nombreProducto)) {
            return false;
        }
        BigDecimal this$precioUnitario = this.getPrecioUnitario();
        BigDecimal other$precioUnitario = other.getPrecioUnitario();
        return !(this$precioUnitario == null ? other$precioUnitario != null : !((Object)this$precioUnitario).equals(other$precioUnitario));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof PedidoRequest;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $productoId = this.getProductoId();
        result = result * 59 + ($productoId == null ? 43 : ((Object)$productoId).hashCode());
        Integer $cantidad = this.getCantidad();
        result = result * 59 + ($cantidad == null ? 43 : ((Object)$cantidad).hashCode());
        String $correoUsuario = this.getCorreoUsuario();
        result = result * 59 + ($correoUsuario == null ? 43 : $correoUsuario.hashCode());
        String $nombreProducto = this.getNombreProducto();
        result = result * 59 + ($nombreProducto == null ? 43 : $nombreProducto.hashCode());
        BigDecimal $precioUnitario = this.getPrecioUnitario();
        result = result * 59 + ($precioUnitario == null ? 43 : ((Object)$precioUnitario).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "PedidoRequest(correoUsuario=" + this.getCorreoUsuario() + ", productoId=" + this.getProductoId() + ", nombreProducto=" + this.getNombreProducto() + ", cantidad=" + this.getCantidad() + ", precioUnitario=" + String.valueOf(this.getPrecioUnitario()) + ")";
    }
}
