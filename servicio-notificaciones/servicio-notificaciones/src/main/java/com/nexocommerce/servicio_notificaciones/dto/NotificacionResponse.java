package com.nexocommerce.servicio_notificaciones.dto;

import com.nexocommerce.servicio_notificaciones.entity.EstadoNotificacion;
import com.nexocommerce.servicio_notificaciones.entity.TipoNotificacion;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

/*
 * DTO utilizado para devolver la información de una notificación al cliente.
 */
@Data
@AllArgsConstructor
public class NotificacionResponse {

    private Long id;
    private String correoUsuario;
    private String titulo;
    private String mensaje;
    private TipoNotificacion tipo;
    private EstadoNotificacion estado;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaEnvio;
}