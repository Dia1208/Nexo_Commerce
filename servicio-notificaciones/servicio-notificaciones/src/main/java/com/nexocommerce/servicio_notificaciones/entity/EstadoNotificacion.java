/*
 * Decompiled with CFR 0.151.
 */
package com.nexocommerce.servicio_notificaciones.entity;

public final class EstadoNotificacion
extends Enum<EstadoNotificacion> {
    public static final /* enum */ EstadoNotificacion PENDIENTE = new EstadoNotificacion();
    public static final /* enum */ EstadoNotificacion ENVIADA = new EstadoNotificacion();
    public static final /* enum */ EstadoNotificacion FALLIDA = new EstadoNotificacion();
    private static final /* synthetic */ EstadoNotificacion[] $VALUES;

    public static EstadoNotificacion[] values() {
        return (EstadoNotificacion[])$VALUES.clone();
    }

    public static EstadoNotificacion valueOf(String name) {
        return Enum.valueOf(EstadoNotificacion.class, name);
    }

    private static /* synthetic */ EstadoNotificacion[] $values() {
        return new EstadoNotificacion[]{PENDIENTE, ENVIADA, FALLIDA};
    }

    static {
        $VALUES = EstadoNotificacion.$values();
    }
}
