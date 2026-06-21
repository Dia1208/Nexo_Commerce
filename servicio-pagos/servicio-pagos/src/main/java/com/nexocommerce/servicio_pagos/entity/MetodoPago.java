/*
 * Decompiled with CFR 0.151.
 */
package com.nexocommerce.servicio_pagos.entity;

public final class MetodoPago
extends Enum<MetodoPago> {
    public static final /* enum */ MetodoPago TARJETA = new MetodoPago();
    public static final /* enum */ MetodoPago TRANSFERENCIA = new MetodoPago();
    public static final /* enum */ MetodoPago EFECTIVO = new MetodoPago();
    private static final /* synthetic */ MetodoPago[] $VALUES;

    public static MetodoPago[] values() {
        return (MetodoPago[])$VALUES.clone();
    }

    public static MetodoPago valueOf(String name) {
        return Enum.valueOf(MetodoPago.class, name);
    }

    private static /* synthetic */ MetodoPago[] $values() {
        return new MetodoPago[]{TARJETA, TRANSFERENCIA, EFECTIVO};
    }

    static {
        $VALUES = MetodoPago.$values();
    }
}
