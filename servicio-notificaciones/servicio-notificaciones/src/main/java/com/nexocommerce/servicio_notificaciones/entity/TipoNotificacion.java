/*
 * Decompiled with CFR 0.151.
 */
package com.nexocommerce.servicio_notificaciones.entity;

public final class TipoNotificacion
extends Enum<TipoNotificacion> {
    public static final /* enum */ TipoNotificacion PEDIDO = new TipoNotificacion();
    public static final /* enum */ TipoNotificacion PAGO = new TipoNotificacion();
    public static final /* enum */ TipoNotificacion SISTEMA = new TipoNotificacion();
    private static final /* synthetic */ TipoNotificacion[] $VALUES;

    public static TipoNotificacion[] values() {
        return (TipoNotificacion[])$VALUES.clone();
    }

    public static TipoNotificacion valueOf(String name) {
        return Enum.valueOf(TipoNotificacion.class, name);
    }

    private static /* synthetic */ TipoNotificacion[] $values() {
        return new TipoNotificacion[]{PEDIDO, PAGO, SISTEMA};
    }

    static {
        $VALUES = TipoNotificacion.$values();
    }
}
