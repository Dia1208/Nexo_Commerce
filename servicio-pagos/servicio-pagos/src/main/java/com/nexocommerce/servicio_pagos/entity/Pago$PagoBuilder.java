/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package com.nexocommerce.servicio_pagos.entity;

import com.nexocommerce.servicio_pagos.entity.EstadoPago;
import com.nexocommerce.servicio_pagos.entity.MetodoPago;
import com.nexocommerce.servicio_pagos.entity.Pago;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Generated;

@Generated
public static class Pago.PagoBuilder {
    @Generated
    private Long id;
    @Generated
    private Long pedidoId;
    @Generated
    private String correoUsuario;
    @Generated
    private BigDecimal monto;
    @Generated
    private MetodoPago metodoPago;
    @Generated
    private EstadoPago estado;
    @Generated
    private LocalDateTime fechaPago;

    @Generated
    Pago.PagoBuilder() {
    }

    @Generated
    public Pago.PagoBuilder id(Long id) {
        this.id = id;
        return this;
    }

    @Generated
    public Pago.PagoBuilder pedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
        return this;
    }

    @Generated
    public Pago.PagoBuilder correoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
        return this;
    }

    @Generated
    public Pago.PagoBuilder monto(BigDecimal monto) {
        this.monto = monto;
        return this;
    }

    @Generated
    public Pago.PagoBuilder metodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
        return this;
    }

    @Generated
    public Pago.PagoBuilder estado(EstadoPago estado) {
        this.estado = estado;
        return this;
    }

    @Generated
    public Pago.PagoBuilder fechaPago(LocalDateTime fechaPago) {
        this.fechaPago = fechaPago;
        return this;
    }

    @Generated
    public Pago build() {
        return new Pago(this.id, this.pedidoId, this.correoUsuario, this.monto, this.metodoPago, this.estado, this.fechaPago);
    }

    @Generated
    public String toString() {
        return "Pago.PagoBuilder(id=" + this.id + ", pedidoId=" + this.pedidoId + ", correoUsuario=" + this.correoUsuario + ", monto=" + String.valueOf(this.monto) + ", metodoPago=" + String.valueOf((Object)this.metodoPago) + ", estado=" + String.valueOf((Object)this.estado) + ", fechaPago=" + String.valueOf(this.fechaPago) + ")";
    }
}
