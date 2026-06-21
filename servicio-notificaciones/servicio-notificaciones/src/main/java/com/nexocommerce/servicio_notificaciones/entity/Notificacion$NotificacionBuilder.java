/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package com.nexocommerce.servicio_notificaciones.entity;

import com.nexocommerce.servicio_notificaciones.entity.EstadoNotificacion;
import com.nexocommerce.servicio_notificaciones.entity.Notificacion;
import com.nexocommerce.servicio_notificaciones.entity.TipoNotificacion;
import java.time.LocalDateTime;
import lombok.Generated;

@Generated
public static class Notificacion.NotificacionBuilder {
    @Generated
    private Long id;
    @Generated
    private String correoUsuario;
    @Generated
    private String titulo;
    @Generated
    private String mensaje;
    @Generated
    private TipoNotificacion tipo;
    @Generated
    private EstadoNotificacion estado;
    @Generated
    private LocalDateTime fechaCreacion;
    @Generated
    private LocalDateTime fechaEnvio;

    @Generated
    Notificacion.NotificacionBuilder() {
    }

    @Generated
    public Notificacion.NotificacionBuilder id(Long id) {
        this.id = id;
        return this;
    }

    @Generated
    public Notificacion.NotificacionBuilder correoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
        return this;
    }

    @Generated
    public Notificacion.NotificacionBuilder titulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    @Generated
    public Notificacion.NotificacionBuilder mensaje(String mensaje) {
        this.mensaje = mensaje;
        return this;
    }

    @Generated
    public Notificacion.NotificacionBuilder tipo(TipoNotificacion tipo) {
        this.tipo = tipo;
        return this;
    }

    @Generated
    public Notificacion.NotificacionBuilder estado(EstadoNotificacion estado) {
        this.estado = estado;
        return this;
    }

    @Generated
    public Notificacion.NotificacionBuilder fechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
        return this;
    }

    @Generated
    public Notificacion.NotificacionBuilder fechaEnvio(LocalDateTime fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
        return this;
    }

    @Generated
    public Notificacion build() {
        return new Notificacion(this.id, this.correoUsuario, this.titulo, this.mensaje, this.tipo, this.estado, this.fechaCreacion, this.fechaEnvio);
    }

    @Generated
    public String toString() {
        return "Notificacion.NotificacionBuilder(id=" + this.id + ", correoUsuario=" + this.correoUsuario + ", titulo=" + this.titulo + ", mensaje=" + this.mensaje + ", tipo=" + String.valueOf((Object)this.tipo) + ", estado=" + String.valueOf((Object)this.estado) + ", fechaCreacion=" + String.valueOf(this.fechaCreacion) + ", fechaEnvio=" + String.valueOf(this.fechaEnvio) + ")";
    }
}
