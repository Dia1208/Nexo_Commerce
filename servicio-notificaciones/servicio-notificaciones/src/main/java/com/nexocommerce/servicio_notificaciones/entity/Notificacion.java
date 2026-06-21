/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  jakarta.persistence.Column
 *  jakarta.persistence.Entity
 *  jakarta.persistence.EnumType
 *  jakarta.persistence.Enumerated
 *  jakarta.persistence.GeneratedValue
 *  jakarta.persistence.GenerationType
 *  jakarta.persistence.Id
 *  jakarta.persistence.Table
 *  lombok.Generated
 */
package com.nexocommerce.servicio_notificaciones.entity;

import com.nexocommerce.servicio_notificaciones.entity.EstadoNotificacion;
import com.nexocommerce.servicio_notificaciones.entity.TipoNotificacion;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Generated;

@Entity
@Table(name="notificaciones")
public class Notificacion {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false)
    private String correoUsuario;
    @Column(nullable=false)
    private String titulo;
    @Column(nullable=false)
    private String mensaje;
    @Enumerated(value=EnumType.STRING)
    @Column(nullable=false)
    private TipoNotificacion tipo;
    @Enumerated(value=EnumType.STRING)
    @Column(nullable=false)
    private EstadoNotificacion estado;
    @Column(nullable=false)
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaEnvio;

    @Generated
    public static NotificacionBuilder builder() {
        return new NotificacionBuilder();
    }

    @Generated
    public Long getId() {
        return this.id;
    }

    @Generated
    public String getCorreoUsuario() {
        return this.correoUsuario;
    }

    @Generated
    public String getTitulo() {
        return this.titulo;
    }

    @Generated
    public String getMensaje() {
        return this.mensaje;
    }

    @Generated
    public TipoNotificacion getTipo() {
        return this.tipo;
    }

    @Generated
    public EstadoNotificacion getEstado() {
        return this.estado;
    }

    @Generated
    public LocalDateTime getFechaCreacion() {
        return this.fechaCreacion;
    }

    @Generated
    public LocalDateTime getFechaEnvio() {
        return this.fechaEnvio;
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
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Generated
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Generated
    public void setTipo(TipoNotificacion tipo) {
        this.tipo = tipo;
    }

    @Generated
    public void setEstado(EstadoNotificacion estado) {
        this.estado = estado;
    }

    @Generated
    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Generated
    public void setFechaEnvio(LocalDateTime fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Notificacion)) {
            return false;
        }
        Notificacion other = (Notificacion)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Long this$id = this.getId();
        Long other$id = other.getId();
        if (this$id == null ? other$id != null : !((Object)this$id).equals(other$id)) {
            return false;
        }
        String this$correoUsuario = this.getCorreoUsuario();
        String other$correoUsuario = other.getCorreoUsuario();
        if (this$correoUsuario == null ? other$correoUsuario != null : !this$correoUsuario.equals(other$correoUsuario)) {
            return false;
        }
        String this$titulo = this.getTitulo();
        String other$titulo = other.getTitulo();
        if (this$titulo == null ? other$titulo != null : !this$titulo.equals(other$titulo)) {
            return false;
        }
        String this$mensaje = this.getMensaje();
        String other$mensaje = other.getMensaje();
        if (this$mensaje == null ? other$mensaje != null : !this$mensaje.equals(other$mensaje)) {
            return false;
        }
        TipoNotificacion this$tipo = this.getTipo();
        TipoNotificacion other$tipo = other.getTipo();
        if (this$tipo == null ? other$tipo != null : !((Object)((Object)this$tipo)).equals((Object)other$tipo)) {
            return false;
        }
        EstadoNotificacion this$estado = this.getEstado();
        EstadoNotificacion other$estado = other.getEstado();
        if (this$estado == null ? other$estado != null : !((Object)((Object)this$estado)).equals((Object)other$estado)) {
            return false;
        }
        LocalDateTime this$fechaCreacion = this.getFechaCreacion();
        LocalDateTime other$fechaCreacion = other.getFechaCreacion();
        if (this$fechaCreacion == null ? other$fechaCreacion != null : !((Object)this$fechaCreacion).equals(other$fechaCreacion)) {
            return false;
        }
        LocalDateTime this$fechaEnvio = this.getFechaEnvio();
        LocalDateTime other$fechaEnvio = other.getFechaEnvio();
        return !(this$fechaEnvio == null ? other$fechaEnvio != null : !((Object)this$fechaEnvio).equals(other$fechaEnvio));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof Notificacion;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        String $correoUsuario = this.getCorreoUsuario();
        result = result * 59 + ($correoUsuario == null ? 43 : $correoUsuario.hashCode());
        String $titulo = this.getTitulo();
        result = result * 59 + ($titulo == null ? 43 : $titulo.hashCode());
        String $mensaje = this.getMensaje();
        result = result * 59 + ($mensaje == null ? 43 : $mensaje.hashCode());
        TipoNotificacion $tipo = this.getTipo();
        result = result * 59 + ($tipo == null ? 43 : ((Object)((Object)$tipo)).hashCode());
        EstadoNotificacion $estado = this.getEstado();
        result = result * 59 + ($estado == null ? 43 : ((Object)((Object)$estado)).hashCode());
        LocalDateTime $fechaCreacion = this.getFechaCreacion();
        result = result * 59 + ($fechaCreacion == null ? 43 : ((Object)$fechaCreacion).hashCode());
        LocalDateTime $fechaEnvio = this.getFechaEnvio();
        result = result * 59 + ($fechaEnvio == null ? 43 : ((Object)$fechaEnvio).hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "Notificacion(id=" + this.getId() + ", correoUsuario=" + this.getCorreoUsuario() + ", titulo=" + this.getTitulo() + ", mensaje=" + this.getMensaje() + ", tipo=" + String.valueOf((Object)this.getTipo()) + ", estado=" + String.valueOf((Object)this.getEstado()) + ", fechaCreacion=" + String.valueOf(this.getFechaCreacion()) + ", fechaEnvio=" + String.valueOf(this.getFechaEnvio()) + ")";
    }

    @Generated
    public Notificacion() {
    }

    @Generated
    public Notificacion(Long id, String correoUsuario, String titulo, String mensaje, TipoNotificacion tipo, EstadoNotificacion estado, LocalDateTime fechaCreacion, LocalDateTime fechaEnvio) {
        this.id = id;
        this.correoUsuario = correoUsuario;
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.tipo = tipo;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.fechaEnvio = fechaEnvio;
    }

    @Generated
    public static class NotificacionBuilder {
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
        NotificacionBuilder() {
        }

        @Generated
        public NotificacionBuilder id(Long id) {
            this.id = id;
            return this;
        }

        @Generated
        public NotificacionBuilder correoUsuario(String correoUsuario) {
            this.correoUsuario = correoUsuario;
            return this;
        }

        @Generated
        public NotificacionBuilder titulo(String titulo) {
            this.titulo = titulo;
            return this;
        }

        @Generated
        public NotificacionBuilder mensaje(String mensaje) {
            this.mensaje = mensaje;
            return this;
        }

        @Generated
        public NotificacionBuilder tipo(TipoNotificacion tipo) {
            this.tipo = tipo;
            return this;
        }

        @Generated
        public NotificacionBuilder estado(EstadoNotificacion estado) {
            this.estado = estado;
            return this;
        }

        @Generated
        public NotificacionBuilder fechaCreacion(LocalDateTime fechaCreacion) {
            this.fechaCreacion = fechaCreacion;
            return this;
        }

        @Generated
        public NotificacionBuilder fechaEnvio(LocalDateTime fechaEnvio) {
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
}
