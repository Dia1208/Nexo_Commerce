package com.nexocommerce.servicio_notificaciones.controller;

import com.nexocommerce.servicio_notificaciones.dto.NotificacionRequest;
import com.nexocommerce.servicio_notificaciones.dto.NotificacionResponse;
import com.nexocommerce.servicio_notificaciones.entity.TipoNotificacion;
import com.nexocommerce.servicio_notificaciones.service.NotificacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
 * Controlador REST del microservicio de notificaciones.
 * Expone endpoints para crear, consultar y actualizar notificaciones.
 */
@RestController
@RequestMapping("/api/notificaciones")
@RequiredArgsConstructor
public class NotificacionController {

    private final NotificacionService notificacionService;

    // Lista todas las notificaciones.
    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(notificacionService.listar());
    }

    // Busca una notificación por id.
    @GetMapping("/{id}")
    public ResponseEntity<NotificacionResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(notificacionService.buscarPorId(id));
    }

    // Lista notificaciones por usuario.
    @GetMapping("/usuario/{correoUsuario}")
    public ResponseEntity<?> listarPorUsuario(@PathVariable String correoUsuario) {
        return ResponseEntity.ok(notificacionService.listarPorUsuario(correoUsuario));
    }

    // Lista notificaciones por tipo.
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<?> listarPorTipo(@PathVariable TipoNotificacion tipo) {
        return ResponseEntity.ok(notificacionService.listarPorTipo(tipo));
    }

    // Crea una nueva notificación.
    @PostMapping
    public ResponseEntity<NotificacionResponse> crear(@Valid @RequestBody NotificacionRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(notificacionService.crear(request));
    }

    // Marca una notificación como enviada.
    @PutMapping("/{id}/enviar")
    public ResponseEntity<NotificacionResponse> marcarComoEnviada(@PathVariable Long id) {
        return ResponseEntity.ok(notificacionService.marcarComoEnviada(id));
    }

    // Marca una notificación como fallida.
    @PutMapping("/{id}/fallar")
    public ResponseEntity<NotificacionResponse> marcarComoFallida(@PathVariable Long id) {
        return ResponseEntity.ok(notificacionService.marcarComoFallida(id));
    }
}