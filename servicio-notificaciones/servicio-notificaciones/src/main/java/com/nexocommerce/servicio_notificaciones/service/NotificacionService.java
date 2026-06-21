package com.nexocommerce.servicio_notificaciones.service;

import com.nexocommerce.servicio_notificaciones.dto.NotificacionRequest;
import com.nexocommerce.servicio_notificaciones.dto.NotificacionResponse;
import com.nexocommerce.servicio_notificaciones.entity.EstadoNotificacion;
import com.nexocommerce.servicio_notificaciones.entity.Notificacion;
import com.nexocommerce.servicio_notificaciones.entity.TipoNotificacion;
import com.nexocommerce.servicio_notificaciones.exception.ResourceNotFoundException;
import com.nexocommerce.servicio_notificaciones.repository.NotificacionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/*
 * Esta clase contiene la lógica de negocio de notificaciones.
 * Aquí se crean, consultan y actualizan estados de notificaciones.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class NotificacionService {

    private final NotificacionRepository notificacionRepository;

    // Lista todas las notificaciones registradas.
    public List<NotificacionResponse> listar() {
        return notificacionRepository.findAll()
                .stream()
                .map(this::mapearAResponse)
                .toList();
    }

    // Busca una notificación por su id.
    public NotificacionResponse buscarPorId(Long id) {
        Notificacion notificacion = notificacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notificación no encontrada con id: " + id));

        return mapearAResponse(notificacion);
    }

    // Lista notificaciones asociadas a un usuario.
    public List<NotificacionResponse> listarPorUsuario(String correoUsuario) {
        return notificacionRepository.findByCorreoUsuario(correoUsuario)
                .stream()
                .map(this::mapearAResponse)
                .toList();
    }

    // Lista notificaciones por tipo.
    public List<NotificacionResponse> listarPorTipo(TipoNotificacion tipo) {
        return notificacionRepository.findByTipo(tipo)
                .stream()
                .map(this::mapearAResponse)
                .toList();
    }

    // Crea una nueva notificación con estado PENDIENTE.
    public NotificacionResponse crear(NotificacionRequest request) {
        Notificacion notificacion = Notificacion.builder()
                .correoUsuario(request.getCorreoUsuario())
                .titulo(request.getTitulo())
                .mensaje(request.getMensaje())
                .tipo(request.getTipo())
                .estado(EstadoNotificacion.PENDIENTE)
                .fechaCreacion(LocalDateTime.now())
                .build();

        Notificacion notificacionGuardada = notificacionRepository.save(notificacion);

        log.info("Notificación creada con id: {}", notificacionGuardada.getId());

        return mapearAResponse(notificacionGuardada);
    }

    // Marca una notificación como enviada.
    public NotificacionResponse marcarComoEnviada(Long id) {
        Notificacion notificacion = notificacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notificación no encontrada con id: " + id));

        notificacion.setEstado(EstadoNotificacion.ENVIADA);
        notificacion.setFechaEnvio(LocalDateTime.now());

        Notificacion notificacionActualizada = notificacionRepository.save(notificacion);

        log.info("Notificación enviada con id: {}", id);

        return mapearAResponse(notificacionActualizada);
    }

    // Marca una notificación como fallida.
    public NotificacionResponse marcarComoFallida(Long id) {
        Notificacion notificacion = notificacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notificación no encontrada con id: " + id));

        notificacion.setEstado(EstadoNotificacion.FALLIDA);

        Notificacion notificacionActualizada = notificacionRepository.save(notificacion);

        log.info("Notificación fallida con id: {}", id);

        return mapearAResponse(notificacionActualizada);
    }

    // Convierte una entidad Notificacion a un DTO NotificacionResponse.
    private NotificacionResponse mapearAResponse(Notificacion notificacion) {
        return new NotificacionResponse(
                notificacion.getId(),
                notificacion.getCorreoUsuario(),
                notificacion.getTitulo(),
                notificacion.getMensaje(),
                notificacion.getTipo(),
                notificacion.getEstado(),
                notificacion.getFechaCreacion(),
                notificacion.getFechaEnvio()
        );
    }
}