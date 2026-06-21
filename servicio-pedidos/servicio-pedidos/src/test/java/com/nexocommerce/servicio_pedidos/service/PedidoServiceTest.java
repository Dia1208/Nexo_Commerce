package com.nexocommerce.servicio_pedidos.service;

import com.nexocommerce.servicio_pedidos.dto.ActualizarEstadoPedidoRequest;
import com.nexocommerce.servicio_pedidos.dto.PedidoRequest;
import com.nexocommerce.servicio_pedidos.dto.PedidoResponse;
import com.nexocommerce.servicio_pedidos.entity.EstadoPedido;
import com.nexocommerce.servicio_pedidos.entity.Pedido;
import com.nexocommerce.servicio_pedidos.exception.ResourceNotFoundException;
import com.nexocommerce.servicio_pedidos.repository.PedidoRepository;
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
 * Pruebas unitarias del servicio de pedidos.
 * Se prueban los métodos de listar, buscar, crear,
 * actualizar estado y cancelar pedido.
 */
@ExtendWith(MockitoExtension.class)
class PedidoServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private PedidoService pedidoService;

    private Pedido pedido;
    private PedidoRequest request;

    @BeforeEach
    void setUp() {
        pedido = Pedido.builder()
                .id(1L)
                .correoUsuario("juan@test.com")
                .productoId(1L)
                .nombreProducto("Teclado Mecánico")
                .cantidad(2)
                .precioUnitario(new BigDecimal("45990"))
                .total(new BigDecimal("91980"))
                .estado(EstadoPedido.PENDIENTE)
                .fechaCreacion(LocalDateTime.now())
                .build();

        request = new PedidoRequest();
        request.setCorreoUsuario("juan@test.com");
        request.setProductoId(1L);
        request.setNombreProducto("Teclado Mecánico");
        request.setCantidad(2);
        request.setPrecioUnitario(new BigDecimal("45990"));
    }

    @Test
    void listarPedidosCorrectamente() {
        when(pedidoRepository.findAll()).thenReturn(List.of(pedido));

        List<PedidoResponse> resultado = pedidoService.listar();

        assertEquals(1, resultado.size());
        assertEquals("juan@test.com", resultado.get(0).getCorreoUsuario());
    }

    @Test
    void buscarPedidoPorIdCorrectamente() {
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));

        PedidoResponse response = pedidoService.buscarPorId(1L);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals(EstadoPedido.PENDIENTE, response.getEstado());
    }

    @Test
    void buscarPedidoNoExistenteLanzaExcepcion() {
        when(pedidoRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> pedidoService.buscarPorId(99L));
    }

    @Test
    void listarPedidosPorUsuarioCorrectamente() {
        when(pedidoRepository.findByCorreoUsuario("juan@test.com")).thenReturn(List.of(pedido));

        List<PedidoResponse> resultado = pedidoService.listarPorUsuario("juan@test.com");

        assertEquals(1, resultado.size());
        assertEquals("juan@test.com", resultado.get(0).getCorreoUsuario());
    }

    @Test
    void crearPedidoCorrectamente() {
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedido);

        PedidoResponse response = pedidoService.crear(request);

        assertNotNull(response);
        assertEquals(new BigDecimal("91980"), response.getTotal());
        assertEquals(EstadoPedido.PENDIENTE, response.getEstado());

        verify(pedidoRepository).save(any(Pedido.class));
    }

    @Test
    void actualizarEstadoPedidoCorrectamente() {
        ActualizarEstadoPedidoRequest estadoRequest = new ActualizarEstadoPedidoRequest();
        estadoRequest.setEstado(EstadoPedido.ENVIADO);

        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedido);

        PedidoResponse response = pedidoService.actualizarEstado(1L, estadoRequest);

        assertNotNull(response);
        assertEquals(EstadoPedido.ENVIADO, pedido.getEstado());

        verify(pedidoRepository).save(pedido);
    }

    @Test
    void cancelarPedidoCorrectamente() {
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedido);

        PedidoResponse response = pedidoService.cancelar(1L);

        assertNotNull(response);
        assertEquals(EstadoPedido.CANCELADO, pedido.getEstado());

        verify(pedidoRepository).save(pedido);
    }
}