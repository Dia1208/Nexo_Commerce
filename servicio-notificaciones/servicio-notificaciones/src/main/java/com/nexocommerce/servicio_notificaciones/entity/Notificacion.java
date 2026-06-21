package com.nexocommerce.servicio_notificaciones.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/*
 * Esta clase representa una notificación dentro del sistema.
 * Guarda mensajes relacionados con pedidos, pagos o avisos generales.
 */
@Entity
@Table(name = "notificaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notificacion {

    // Identificador único de la notificación.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Correo del usuario que recibirá la notificación.
    @Column(nullable = false)
    private String correoUsuario;

    // Título de la notificación.
    @Column(nullable = false)
    private String titulo;

    // Mensaje principal de la notificación.
    @Column(nullable = false)
    private String mensaje;

    // Tipo de notificación: PEDIDO, PAGO o SISTEMA.
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoNotificacion tipo;

    // Estado actual de la notificación.
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoNotificacion estado;

    // Fecha en la que se creó la notificación.
    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    // Fecha en la que fue enviada. Puede quedar vacía si aún no se envía.
    private LocalDateTime fechaEnvio;
}