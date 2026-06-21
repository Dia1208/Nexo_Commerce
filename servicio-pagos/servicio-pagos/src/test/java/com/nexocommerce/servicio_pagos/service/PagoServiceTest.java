package com.nexocommerce.servicio_pagos.service;

import com.nexocommerce.servicio_pagos.dto.PagoRequest;
import com.nexocommerce.servicio_pagos.dto.PagoResponse;
import com.nexocommerce.servicio_pagos.entity.EstadoPago;
import com.nexocommerce.servicio_pagos.entity.MetodoPago;
import com.nexocommerce.servicio_pagos.entity.Pago;
import com.nexocommerce.servicio_pagos.exception.ResourceNotFoundException;
import com.nexocommerce.servicio_pagos.repository.PagoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/*
 * Pruebas unitarias del servicio de pagos.
 * Se prueban los métodos de listar, buscar, crear,
 * aprobar y rechazar pagos.
 */
@ExtendWith(MockitoExtension.class)
class PagoServiceTest {

    @Mock
    private PagoRepository pagoRepository;

    @InjectMocks
    private PagoService pagoService;

    private Pago pago;
    private PagoRequest request;

    @BeforeEach
    void setUp() {
        pago = Pago.builder()
                .id(1L)
                .pedidoId(1L)
                .correoUsuario("juan@test.com")
                .monto(new BigDecimal("350000"))
                .metodoPago(MetodoPago.TARJETA)
                .estado(EstadoPago.PENDIENTE)
                .fechaPago(LocalDateTime.now())
                .build();

        request = new PagoRequest();
        request.setPedidoId(1L);
        request.setCorreoUsuario("juan@test.com");
        request.setMonto(new BigDecimal("350000"));
        request.setMetodoPago(MetodoPago.TARJETA);
    }

    @Test
    void listarPagosCorrectamente() {
        when(pagoRepository.findAll()).thenReturn(List.of(pago));

        List<PagoResponse> resultado = pagoService.listar();

        assertEquals(1, resultado.size());
        assertEquals("juan@test.com", resultado.get(0).getCorreoUsuario());
    }

    @Test
    void buscarPagoPorIdCorrectamente() {
        when(pagoRepository.findById(1L)).thenReturn(Optional.of(pago));

        PagoResponse response = pagoService.buscarPorId(1L);

        assertNotNull(response);
        assertEquals(1L, response.getId());
    }

    @Test
    void buscarPagoNoExistenteLanzaExcepcion() {
        when(pagoRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> pagoService.buscarPorId(99L));
    }

    @Test
    void listarPagosPorPedidoCorrectamente() {
        when(pagoRepository.findByPedidoId(1L)).thenReturn(List.of(pago));

        List<PagoResponse> resultado = pagoService.listarPorPedido(1L);

        assertEquals(1, resultado.size());
        assertEquals(1L, resultado.get(0).getPedidoId());
    }

    @Test
    void listarPagosPorUsuarioCorrectamente() {
        when(pagoRepository.findByCorreoUsuario("juan@test.com")).thenReturn(List.of(pago));

        List<PagoResponse> resultado = pagoService.listarPorUsuario("juan@test.com");

        assertEquals(1, resultado.size());
        assertEquals("juan@test.com", resultado.get(0).getCorreoUsuario());
    }

    @Test
    void crearPagoCorrectamente() {
        when(pagoRepository.save(any(Pago.class))).thenReturn(pago);

        PagoResponse response = pagoService.crear(request);

        assertNotNull(response);
        assertEquals(EstadoPago.PENDIENTE, response.getEstado());

        verify(pagoRepository).save(any(Pago.class));
    }

    @Test
    void aprobarPagoCorrectamente() {
        when(pagoRepository.findById(1L)).thenReturn(Optional.of(pago));
        when(pagoRepository.save(any(Pago.class))).thenReturn(pago);

        PagoResponse response = pagoService.aprobar(1L);

        assertNotNull(response);
        assertEquals(EstadoPago.APROBADO, pago.getEstado());

        verify(pagoRepository).save(pago);
    }

    @Test
    void rechazarPagoCorrectamente() {
        when(pagoRepository.findById(1L)).thenReturn(Optional.of(pago));
        when(pagoRepository.save(any(Pago.class))).thenReturn(pago);

        PagoResponse response = pagoService.rechazar(1L);

        assertNotNull(response);
        assertEquals(EstadoPago.RECHAZADO, pago.getEstado());

        verify(pagoRepository).save(pago);
    }
}