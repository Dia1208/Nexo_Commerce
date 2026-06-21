package com.nexocommerce.servicio_notificaciones.service;

import com.nexocommerce.servicio_notificaciones.dto.NotificacionRequest;
import com.nexocommerce.servicio_notificaciones.dto.NotificacionResponse;
import com.nexocommerce.servicio_notificaciones.entity.EstadoNotificacion;
import com.nexocommerce.servicio_notificaciones.entity.Notificacion;
import com.nexocommerce.servicio_notificaciones.entity.TipoNotificacion;
import com.nexocommerce.servicio_notificaciones.exception.ResourceNotFoundException;
import com.nexocommerce.servicio_notificaciones.repository.NotificacionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/*
 * Pruebas unitarias del servicio de notificaciones.
 * Se prueban los métodos de listar, buscar, crear
 * y actualizar el estado de una notificación.
 */
@ExtendWith(MockitoExtension.class)
class NotificacionServiceTest {

    @Mock
    private NotificacionRepository notificacionRepository;

    @InjectMocks
    private NotificacionService notificacionService;

    private Notificacion notificacion;
    private NotificacionRequest request;

    @BeforeEach
    void setUp() {
        notificacion = Notificacion.builder()
                .id(1L)
                .correoUsuario("juan@test.com")
                .titulo("Pago aprobado")
                .mensaje("Tu pago fue aprobado correctamente")
                .tipo(TipoNotificacion.PAGO)
                .estado(EstadoNotificacion.PENDIENTE)
                .fechaCreacion(LocalDateTime.now())
                .fechaEnvio(null)
                .build();

        request = new NotificacionRequest();
        request.setCorreoUsuario("juan@test.com");
        request.setTitulo("Pago aprobado");
        request.setMensaje("Tu pago fue aprobado correctamente");
        request.setTipo(TipoNotificacion.PAGO);
    }

    @Test
    void listarNotificacionesCorrectamente() {
        when(notificacionRepository.findAll()).thenReturn(List.of(notificacion));

        List<NotificacionResponse> resultado = notificacionService.listar();

        assertEquals(1, resultado.size());
        assertEquals("juan@test.com", resultado.get(0).getCorreoUsuario());
    }

    @Test
    void buscarNotificacionPorIdCorrectamente() {
        when(notificacionRepository.findById(1L)).thenReturn(Optional.of(notificacion));

        NotificacionResponse response = notificacionService.buscarPorId(1L);

        assertNotNull(response);
        assertEquals(1L, response.getId());
    }

    @Test
    void buscarNotificacionNoExistenteLanzaExcepcion() {
        when(notificacionRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> notificacionService.buscarPorId(99L));
    }

    @Test
    void listarNotificacionesPorUsuarioCorrectamente() {
        when(notificacionRepository.findByCorreoUsuario("juan@test.com"))
                .thenReturn(List.of(notificacion));

        List<NotificacionResponse> resultado =
                notificacionService.listarPorUsuario("juan@test.com");

        assertEquals(1, resultado.size());
        assertEquals("juan@test.com", resultado.get(0).getCorreoUsuario());
    }

    @Test
    void listarNotificacionesPorTipoCorrectamente() {
        when(notificacionRepository.findByTipo(TipoNotificacion.PAGO))
                .thenReturn(List.of(notificacion));

        List<NotificacionResponse> resultado =
                notificacionService.listarPorTipo(TipoNotificacion.PAGO);

        assertEquals(1, resultado.size());
        assertEquals(TipoNotificacion.PAGO, resultado.get(0).getTipo());
    }

    @Test
    void crearNotificacionCorrectamente() {
        when(notificacionRepository.save(any(Notificacion.class))).thenReturn(notificacion);

        NotificacionResponse response = notificacionService.crear(request);

        assertNotNull(response);
        assertEquals(EstadoNotificacion.PENDIENTE, response.getEstado());

        verify(notificacionRepository).save(any(Notificacion.class));
    }

    @Test
    void marcarComoEnviadaCorrectamente() {
        when(notificacionRepository.findById(1L)).thenReturn(Optional.of(notificacion));
        when(notificacionRepository.save(any(Notificacion.class))).thenReturn(notificacion);

        NotificacionResponse response = notificacionService.marcarComoEnviada(1L);

        assertNotNull(response);
        assertEquals(EstadoNotificacion.ENVIADA, notificacion.getEstado());
        assertNotNull(notificacion.getFechaEnvio());

        verify(notificacionRepository).save(notificacion);
    }

    @Test
    void marcarComoFallidaCorrectamente() {
        when(notificacionRepository.findById(1L)).thenReturn(Optional.of(notificacion));
        when(notificacionRepository.save(any(Notificacion.class))).thenReturn(notificacion);

        NotificacionResponse response = notificacionService.marcarComoFallida(1L);

        assertNotNull(response);
        assertEquals(EstadoNotificacion.FALLIDA, notificacion.getEstado());

        verify(notificacionRepository).save(notificacion);
    }
}