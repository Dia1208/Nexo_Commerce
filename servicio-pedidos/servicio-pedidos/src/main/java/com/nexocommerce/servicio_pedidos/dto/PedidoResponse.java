/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package com.nexocommerce.servicio_pedidos.dto;

import com.nexocommerce.servicio_pedidos.entity.EstadoPedido;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Generated;

public class PedidoResponse {
    private Long id;
    private String correoUsuario;
    private Long productoId;
    private String nombreProducto;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal total;
    private EstadoPedido estado;
    private LocalDateTime fechaCreacion;

    @Generated
    public Long getId() {
        return this.id;
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
    public BigDecimal getTotal() {
        return this.total;
    }

    @Generated
    public EstadoPedido getEstado() {
        return this.estado;
    }

    @Generated
    public LocalDateTime getFechaCreacion() {
        return this.fechaCreacion;
    }

    @Generated
    public void setId(Long id) {
        this.id = id;
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
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Generated
    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    @Generated
    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof PedidoResponse)) {
            return false;
        }
        PedidoResponse other = (PedidoResponse)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Long this$id = this.getId();
        Long other$id = other.getId();
        if (this$id == null ? other$id != null : !((Object)this$id).equals(other$id)) {
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
        if (this$precioUnitario == null ? other$precioUnitario != null : !((Object)this$precioUnitario).equals(other$precioUnitario)) {
            return false;
        }
        BigDecimal this$total = this.getTotal();
        BigDecimal other$total = other.getTotal();
        if (this$total == null ? other$total != null : !((Object)this$total).equals(other$total)) {
            return false;
        }
        EstadoPedido this$estado = this.getEstado();
        EstadoPedido other$estado = other.getEstado();
        if (this$estado == null ? other$estado != null : !((Object)((Object)this$estado)).equals((Object)other$estado)) {
            return false;
        }
        LocalDateTime this$fechaCreacion = this.getFechaCreacion();
        LocalDateTime other$fechaCreacion = other.getFechaCreacion();
        return !(this$fechaCreacion == null ? other$fechaCreacion != null : !((Object)this$fechaCreacion).equals(other$fechaCreacion));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof PedidoResponse;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
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
        BigDecimal $total = this.getTotal();
        result = result * 59 + ($total == null ? 43 : ((Object)$total).hashCode());
        EstadoPedido $estado = this.getEstado();
        result = result * 59 + ($estado == null ? 43 : ((Object)((Object)$estado)).hashCode());
        LocalDateTime $fechaCreacion = this.getFechaCreacion();
        result = result * 59 + ($fechaCreacion == null ? 43 : ((Object)$fechaCreacion).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "PedidoResponse(id=" + this.getId() + ", correoUsuario=" + this.getCorreoUsuario() + ", productoId=" + this.getProductoId() + ", nombreProducto=" + this.getNombreProducto() + ", cantidad=" + this.getCantidad() + ", precioUnitario=" + String.valueOf(this.getPrecioUnitario()) + ", total=" + String.valueOf(this.getTotal()) + ", estado=" + String.valueOf((Object)this.getEstado()) + ", fechaCreacion=" + String.valueOf(this.getFechaCreacion()) + ")";
    }

    @Generated
    public PedidoResponse(Long id, String correoUsuario, Long productoId, String nombreProducto, Integer cantidad, BigDecimal precioUnitario, BigDecimal total, EstadoPedido estado, LocalDateTime fechaCreacion) {
        this.id = id;
        this.correoUsuario = correoUsuario;
        this.productoId = productoId;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.total = total;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
    }
}
