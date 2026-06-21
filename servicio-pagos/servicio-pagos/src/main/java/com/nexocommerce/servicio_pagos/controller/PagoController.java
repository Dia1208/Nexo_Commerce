package com.nexocommerce.servicio_pagos.controller;

import com.nexocommerce.servicio_pagos.dto.PagoRequest;
import com.nexocommerce.servicio_pagos.dto.PagoResponse;
import com.nexocommerce.servicio_pagos.service.PagoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
 * Controlador REST del microservicio de pagos.
 * Expone endpoints para registrar y consultar pagos.
 */
@RestController
@RequestMapping("/api/pagos")
@RequiredArgsConstructor
public class PagoController {

    private final PagoService pagoService;

    // Lista todos los pagos.
    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(pagoService.listar());
    }

    // Busca un pago por id.
    @GetMapping("/{id}")
    public ResponseEntity<PagoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pagoService.buscarPorId(id));
    }

    // Lista pagos por pedido.
    @GetMapping("/pedido/{pedidoId}")
    public ResponseEntity<?> listarPorPedido(@PathVariable Long pedidoId) {
        return ResponseEntity.ok(pagoService.listarPorPedido(pedidoId));
    }

    // Lista pagos por usuario.
    @GetMapping("/usuario/{correoUsuario}")
    public ResponseEntity<?> listarPorUsuario(@PathVariable String correoUsuario) {
        return ResponseEntity.ok(pagoService.listarPorUsuario(correoUsuario));
    }

    // Crea un nuevo pago.
    @PostMapping
    public ResponseEntity<PagoResponse> crear(@Valid @RequestBody PagoRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(pagoService.crear(request));
    }

    // Aprueba un pago.
    @PutMapping("/{id}/aprobar")
    public ResponseEntity<PagoResponse> aprobar(@PathVariable Long id) {
        return ResponseEntity.ok(pagoService.aprobar(id));
    }

    // Rechaza un pago.
    @PutMapping("/{id}/rechazar")
    public ResponseEntity<PagoResponse> rechazar(@PathVariable Long id) {
        return ResponseEntity.ok(pagoService.rechazar(id));
    }
}