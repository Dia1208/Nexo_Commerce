package com.nexocommerce.servicio_notificaciones.dto;

import com.nexocommerce.servicio_notificaciones.entity.TipoNotificacion;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/*
 * DTO utilizado para recibir los datos necesarios
 * al crear una notificación.
 */
@Data
public class NotificacionRequest {

    // Correo del usuario que recibirá la notificación.
    @NotBlank(message = "El correo del usuario es obligatorio")
    private String correoUsuario;

    // Título de la notificación.
    @NotBlank(message = "El título es obligatorio")
    private String titulo;

    // Mensaje de la notificación.
    @NotBlank(message = "El mensaje es obligatorio")
    private String mensaje;

    // Tipo de notificación.
    @NotNull(message = "El tipo de notificación es obligatorio")
    private TipoNotificacion tipo;
}