package com.nexocommerce.servicio_pedidos.controller;

import com.nexocommerce.servicio_pedidos.dto.ActualizarEstadoPedidoRequest;
import com.nexocommerce.servicio_pedidos.dto.PedidoRequest;
import com.nexocommerce.servicio_pedidos.dto.PedidoResponse;
import com.nexocommerce.servicio_pedidos.service.PedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * Controlador REST del microservicio de pedidos.
 * Expone los endpoints necesarios para crear, consultar,
 * actualizar y cancelar pedidos dentro del sistema.
 */
@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    // Lista todos los pedidos registrados.
    @GetMapping
    public ResponseEntity<List<PedidoResponse>> listar() {
        return ResponseEntity.ok(pedidoService.listar());
    }

    // Busca un pedido por su identificador.
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.buscarPorId(id));
    }

    // Lista los pedidos asociados al correo de un usuario.
    @GetMapping("/usuario/{correo}")
    public ResponseEntity<List<PedidoResponse>> listarPorUsuario(@PathVariable String correo) {
        return ResponseEntity.ok(pedidoService.listarPorUsuario(correo));
    }

    // Crea un nuevo pedido.
    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody PedidoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.crear(request));
    }

    // Actualiza el estado de un pedido.
    @PutMapping("/{id}/estado")
    public ResponseEntity<?> actualizarEstado(
            @PathVariable Long id,
            @Valid @RequestBody ActualizarEstadoPedidoRequest request) {
        return ResponseEntity.ok(pedidoService.actualizarEstado(id, request));
    }

    // Cancela un pedido existente.
    @PutMapping("/{id}/cancelar")
    public ResponseEntity<?> cancelar(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.cancelar(id));
    }
}