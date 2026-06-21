/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.stereotype.Service
 */
package com.nexocommerce.servicio_pedidos.service;

import com.nexocommerce.servicio_pedidos.dto.ActualizarEstadoPedidoRequest;
import com.nexocommerce.servicio_pedidos.dto.PedidoRequest;
import com.nexocommerce.servicio_pedidos.dto.PedidoResponse;
import com.nexocommerce.servicio_pedidos.entity.EstadoPedido;
import com.nexocommerce.servicio_pedidos.entity.Pedido;
import com.nexocommerce.servicio_pedidos.exception.ResourceNotFoundException;
import com.nexocommerce.servicio_pedidos.repository.PedidoRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {
    @Generated
    private static final Logger log = LoggerFactory.getLogger(PedidoService.class);
    private final PedidoRepository pedidoRepository;

    public List<PedidoResponse> listar() {
        return this.pedidoRepository.findAll().stream().map(this::mapearAResponse).toList();
    }

    public PedidoResponse buscarPorId(Long id) {
        Pedido pedido = (Pedido)this.pedidoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado con id: " + id));
        return this.mapearAResponse(pedido);
    }

    public List<PedidoResponse> listarPorUsuario(String correoUsuario) {
        return this.pedidoRepository.findByCorreoUsuario(correoUsuario).stream().map(this::mapearAResponse).toList();
    }

    public PedidoResponse crear(PedidoRequest request) {
        BigDecimal total = request.getPrecioUnitario().multiply(BigDecimal.valueOf(request.getCantidad().intValue()));
        Pedido pedido = Pedido.builder().correoUsuario(request.getCorreoUsuario()).productoId(request.getProductoId()).nombreProducto(request.getNombreProducto()).cantidad(request.getCantidad()).precioUnitario(request.getPrecioUnitario()).total(total).estado(EstadoPedido.PENDIENTE).fechaCreacion(LocalDateTime.now()).build();
        Pedido pedidoGuardado = (Pedido)this.pedidoRepository.save(pedido);
        log.info("Pedido creado con id: {}", (Object)pedidoGuardado.getId());
        return this.mapearAResponse(pedidoGuardado);
    }

    public PedidoResponse actualizarEstado(Long id, ActualizarEstadoPedidoRequest request) {
        Pedido pedido = (Pedido)this.pedidoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado con id: " + id));
        pedido.setEstado(request.getEstado());
        Pedido pedidoActualizado = (Pedido)this.pedidoRepository.save(pedido);
        log.info("Pedido actualizado con estado: {}", (Object)request.getEstado());
        return this.mapearAResponse(pedidoActualizado);
    }

    public PedidoResponse cancelar(Long id) {
        Pedido pedido = (Pedido)this.pedidoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado con id: " + id));
        pedido.setEstado(EstadoPedido.CANCELADO);
        Pedido pedidoCancelado = (Pedido)this.pedidoRepository.save(pedido);
        log.info("Pedido cancelado con id: {}", (Object)id);
        return this.mapearAResponse(pedidoCancelado);
    }

    private PedidoResponse mapearAResponse(Pedido pedido) {
        return new PedidoResponse(pedido.getId(), pedido.getCorreoUsuario(), pedido.getProductoId(), pedido.getNombreProducto(), pedido.getCantidad(), pedido.getPrecioUnitario(), pedido.getTotal(), pedido.getEstado(), pedido.getFechaCreacion());
    }

    @Generated
    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }
}
