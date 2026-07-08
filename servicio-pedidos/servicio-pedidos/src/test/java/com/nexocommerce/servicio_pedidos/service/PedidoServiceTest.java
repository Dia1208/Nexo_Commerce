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
 *
 * Se prueban los métodos principales:
 * listar, buscar por ID, listar por usuario, crear pedido,
 * actualizar estado y cancelar pedido.
 *
 * Ahora servicio-pedidos no consulta directamente servicio-productos.
 * Esa responsabilidad quedó en servicio-checkout.
 * Por eso este test valida que servicio-pedidos reciba el pedido ya calculado
 * y lo guarde correctamente.
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

        /*
         * Ahora PedidoRequest recibe los datos ya calculados.
         * Estos datos vienen desde servicio-checkout.
         */
        request = new PedidoRequest();
        request.setCorreoUsuario("juan@test.com");
        request.setProductoId(1L);
        request.setNombreProducto("Teclado Mecánico");
        request.setCantidad(2);
        request.setPrecioUnitario(new BigDecimal("45990"));
        request.setTotal(new BigDecimal("91980"));
    }

    @Test
    void listarPedidosCorrectamente() {
        // Given
        when(pedidoRepository.findAll()).thenReturn(List.of(pedido));

        // When
        List<PedidoResponse> resultado = pedidoService.listar();

        // Then
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("juan@test.com", resultado.get(0).getCorreoUsuario());
        assertEquals("Teclado Mecánico", resultado.get(0).getNombreProducto());
        assertEquals(new BigDecimal("91980"), resultado.get(0).getTotal());

        verify(pedidoRepository, times(1)).findAll();
    }

    @Test
    void buscarPedidoPorIdCorrectamente() {
        // Given
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));

        // When
        PedidoResponse response = pedidoService.buscarPorId(1L);

        // Then
        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("juan@test.com", response.getCorreoUsuario());
        assertEquals("Teclado Mecánico", response.getNombreProducto());
        assertEquals(EstadoPedido.PENDIENTE, response.getEstado());
        assertEquals(new BigDecimal("91980"), response.getTotal());

        verify(pedidoRepository, times(1)).findById(1L);
    }

    @Test
    void buscarPedidoNoExistenteLanzaExcepcion() {
        // Given
        when(pedidoRepository.findById(99L)).thenReturn(Optional.empty());

        // When / Then
        assertThrows(ResourceNotFoundException.class,
                () -> pedidoService.buscarPorId(99L));

        verify(pedidoRepository, times(1)).findById(99L);
    }

    @Test
    void listarPedidosPorUsuarioCorrectamente() {
        // Given
        when(pedidoRepository.findByCorreoUsuario("juan@test.com")).thenReturn(List.of(pedido));

        // When
        List<PedidoResponse> resultado = pedidoService.listarPorUsuario("juan@test.com");

        // Then
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("juan@test.com", resultado.get(0).getCorreoUsuario());

        verify(pedidoRepository, times(1)).findByCorreoUsuario("juan@test.com");
    }

    @Test
    void crearPedidoCorrectamente() {
        // Given
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedido);

        // When
        PedidoResponse response = pedidoService.crear(request);

        // Then
        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("juan@test.com", response.getCorreoUsuario());
        assertEquals(1L, response.getProductoId());
        assertEquals("Teclado Mecánico", response.getNombreProducto());
        assertEquals(2, response.getCantidad());
        assertEquals(new BigDecimal("45990"), response.getPrecioUnitario());
        assertEquals(new BigDecimal("91980"), response.getTotal());
        assertEquals(EstadoPedido.PENDIENTE, response.getEstado());

        verify(pedidoRepository, times(1)).save(any(Pedido.class));
    }

    @Test
    void actualizarEstadoPedidoCorrectamente() {
        // Given
        ActualizarEstadoPedidoRequest estadoRequest = new ActualizarEstadoPedidoRequest();
        estadoRequest.setEstado(EstadoPedido.ENVIADO);

        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedido);

        // When
        PedidoResponse response = pedidoService.actualizarEstado(1L, estadoRequest);

        // Then
        assertNotNull(response);
        assertEquals(EstadoPedido.ENVIADO, pedido.getEstado());
        assertNotEquals(EstadoPedido.PENDIENTE, pedido.getEstado());

        verify(pedidoRepository, times(1)).findById(1L);
        verify(pedidoRepository, times(1)).save(pedido);
    }

    @Test
    void cancelarPedidoCorrectamente() {
        // Given
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedido);

        // When
        PedidoResponse response = pedidoService.cancelar(1L);

        // Then
        assertNotNull(response);
        assertEquals(EstadoPedido.CANCELADO, pedido.getEstado());
        assertNotEquals(EstadoPedido.PENDIENTE, pedido.getEstado());

        verify(pedidoRepository, times(1)).findById(1L);
        verify(pedidoRepository, times(1)).save(pedido);
    }

    @Test
    void eliminarPedidoCorrectamente() {
        // Given
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));

        // When
        pedidoService.eliminar(1L);

        // Then
        verify(pedidoRepository, times(1)).findById(1L);
        verify(pedidoRepository, times(1)).delete(pedido);
    }
}