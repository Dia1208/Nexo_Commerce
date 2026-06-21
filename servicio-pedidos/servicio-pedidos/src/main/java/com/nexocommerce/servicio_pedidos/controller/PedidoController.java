/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  jakarta.validation.Valid
 *  lombok.Generated
 *  org.springframework.http.HttpStatus
 *  org.springframework.http.HttpStatusCode
 *  org.springframework.http.ResponseEntity
 *  org.springframework.web.bind.annotation.GetMapping
 *  org.springframework.web.bind.annotation.PathVariable
 *  org.springframework.web.bind.annotation.PostMapping
 *  org.springframework.web.bind.annotation.PutMapping
 *  org.springframework.web.bind.annotation.RequestBody
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RestController
 */
package com.nexocommerce.servicio_pedidos.controller;

import com.nexocommerce.servicio_pedidos.dto.ActualizarEstadoPedidoRequest;
import com.nexocommerce.servicio_pedidos.dto.PedidoRequest;
import com.nexocommerce.servicio_pedidos.dto.PedidoResponse;
import com.nexocommerce.servicio_pedidos.service.PedidoService;
import jakarta.validation.Valid;
import lombok.Generated;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/api/pedidos"})
public class PedidoController {
    private final PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(this.pedidoService.listar());
    }

    @GetMapping(value={"/{id}"})
    public ResponseEntity<PedidoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok((Object)this.pedidoService.buscarPorId(id));
    }

    @GetMapping(value={"/usuario/{correo}"})
    public ResponseEntity<?> listarPorUsuario(@PathVariable String correo) {
        return ResponseEntity.ok(this.pedidoService.listarPorUsuario(correo));
    }

    @PostMapping
    public ResponseEntity<PedidoResponse> crear(@Valid @RequestBody PedidoRequest request) {
        return ResponseEntity.status((HttpStatusCode)HttpStatus.CREATED).body((Object)this.pedidoService.crear(request));
    }

    @PutMapping(value={"/{id}/estado"})
    public ResponseEntity<PedidoResponse> actualizarEstado(@PathVariable Long id, @Valid @RequestBody ActualizarEstadoPedidoRequest request) {
        return ResponseEntity.ok((Object)this.pedidoService.actualizarEstado(id, request));
    }

    @PutMapping(value={"/{id}/cancelar"})
    public ResponseEntity<PedidoResponse> cancelar(@PathVariable Long id) {
        return ResponseEntity.ok((Object)this.pedidoService.cancelar(id));
    }

    @Generated
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }
}
