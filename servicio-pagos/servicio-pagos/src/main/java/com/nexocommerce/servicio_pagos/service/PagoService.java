package com.nexocommerce.servicio_pagos.service;

import com.nexocommerce.servicio_pagos.dto.PagoRequest;
import com.nexocommerce.servicio_pagos.dto.PagoResponse;
import com.nexocommerce.servicio_pagos.entity.EstadoPago;
import com.nexocommerce.servicio_pagos.entity.Pago;
import com.nexocommerce.servicio_pagos.exception.ResourceNotFoundException;
import com.nexocommerce.servicio_pagos.repository.PagoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/*
 * Esta clase contiene la lógica de negocio de pagos.
 * Aquí se crean, consultan, aprueban y rechazan pagos.
 */
@Service

/*
 * Genera automáticamente el constructor para inyectar
 * el repositorio PagoRepository en este servicio.
 */
@RequiredArgsConstructor
public class PagoService {

    private final PagoRepository pagoRepository;

    // Lista todos los pagos registrados.
    public List<PagoResponse> listar() {
        return pagoRepository.findAll()
                .stream()
                .map(this::mapearAResponse)
                .toList();
    }

    // Busca un pago por su id.
    public PagoResponse buscarPorId(Long id) {
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pago no encontrado con id: " + id));

        return mapearAResponse(pago);
    }

    // Lista los pagos asociados a un pedido.
    public List<PagoResponse> listarPorPedido(Long pedidoId) {
        return pagoRepository.findByPedidoId(pedidoId)
                .stream()
                .map(this::mapearAResponse)
                .toList();
    }

    // Lista los pagos asociados a un usuario.
    public List<PagoResponse> listarPorUsuario(String correoUsuario) {
        return pagoRepository.findByCorreoUsuario(correoUsuario)
                .stream()
                .map(this::mapearAResponse)
                .toList();
    }

    // Registra un pago nuevo con estado PENDIENTE.
    public PagoResponse crear(PagoRequest request) {
        Pago pago = Pago.builder()
                .pedidoId(request.getPedidoId())
                .correoUsuario(request.getCorreoUsuario())
                .monto(request.getMonto())
                .metodoPago(request.getMetodoPago())
                .estado(EstadoPago.PENDIENTE)
                .fechaPago(LocalDateTime.now())
                .build();

        Pago pagoGuardado = pagoRepository.save(pago);

        return mapearAResponse(pagoGuardado);
    }

    // Cambia el estado de un pago a APROBADO.
    public PagoResponse aprobar(Long id) {
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pago no encontrado con id: " + id));

        pago.setEstado(EstadoPago.APROBADO);

        Pago pagoActualizado = pagoRepository.save(pago);

        return mapearAResponse(pagoActualizado);
    }

    // Cambia el estado de un pago a RECHAZADO.
    public PagoResponse rechazar(Long id) {
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pago no encontrado con id: " + id));

        pago.setEstado(EstadoPago.RECHAZADO);

        Pago pagoActualizado = pagoRepository.save(pago);

        return mapearAResponse(pagoActualizado);
    }

    // Convierte una entidad Pago a un DTO PagoResponse.
    private PagoResponse mapearAResponse(Pago pago) {
        return new PagoResponse(
                pago.getId(),
                pago.getPedidoId(),
                pago.getCorreoUsuario(),
                pago.getMonto(),
                pago.getMetodoPago(),
                pago.getEstado(),
                pago.getFechaPago()
        );
    }
}