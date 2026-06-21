/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  jakarta.validation.constraints.NotNull
 *  lombok.Generated
 */
package com.nexocommerce.servicio_pedidos.dto;

import com.nexocommerce.servicio_pedidos.entity.EstadoPedido;
import jakarta.validation.constraints.NotNull;
import lombok.Generated;

public class ActualizarEstadoPedidoRequest {
    @NotNull(message="El estado es obligatorio")
    private @NotNull(message="El estado es obligatorio") EstadoPedido estado;

    @Generated
    public ActualizarEstadoPedidoRequest() {
    }

    @Generated
    public EstadoPedido getEstado() {
        return this.estado;
    }

    @Generated
    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ActualizarEstadoPedidoRequest)) {
            return false;
        }
        ActualizarEstadoPedidoRequest other = (ActualizarEstadoPedidoRequest)o;
        if (!other.canEqual(this)) {
            return false;
        }
        EstadoPedido this$estado = this.getEstado();
        EstadoPedido other$estado = other.getEstado();
        return !(this$estado == null ? other$estado != null : !((Object)((Object)this$estado)).equals((Object)other$estado));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof ActualizarEstadoPedidoRequest;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        EstadoPedido $estado = this.getEstado();
        result = result * 59 + ($estado == null ? 43 : ((Object)((Object)$estado)).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "ActualizarEstadoPedidoRequest(estado=" + String.valueOf((Object)this.getEstado()) + ")";
    }
}
