package com.nexocommerce.servicio_pedidos.service;

import com.nexocommerce.servicio_pedidos.cliente.ProductoClient;
import com.nexocommerce.servicio_pedidos.dto.ActualizarEstadoPedidoRequest;
import com.nexocommerce.servicio_pedidos.dto.PedidoRequest;
import com.nexocommerce.servicio_pedidos.dto.PedidoResponse;
import com.nexocommerce.servicio_pedidos.dto.ProductoResponse;
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
 *
 * También se prueba la integración simulada con ProductoClient,
 * que representa la comunicación con el microservicio de productos.
 */
@ExtendWith(MockitoExtension.class)
class PedidoServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private ProductoClient productoClient;

    @InjectMocks
    private PedidoService pedidoService;

    private Pedido pedido;
    private PedidoRequest request;
    private ProductoResponse productoResponse;

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
         * Ahora PedidoRequest solo recibe correoUsuario, productoId y cantidad.
         * El nombre y precio del producto se obtienen desde ProductoClient.
         */
        request = new PedidoRequest();
        request.setCorreoUsuario("juan@test.com");
        request.setProductoId(1L);
        request.setCantidad(2);

        /*
         * Respuesta simulada del microservicio de productos.
         */
        productoResponse = new ProductoResponse();
        productoResponse.setId(1L);
        productoResponse.setNombre("Teclado Mecánico");
        productoResponse.setDescripcion("Teclado gamer RGB");
        productoResponse.setPrecio(new BigDecimal("45990"));
        productoResponse.setStock(10);
        productoResponse.setCategoria("Tecnología");
    }

    @Test
    void listarPedidosCorrectamente() {
        // Given
        when(pedidoRepository.findAll()).thenReturn(List.of(pedido));

        // When
        List<PedidoResponse> resultado = pedidoService.listar();

        // Then
        assertEquals(1, resultado.size());
        assertEquals("juan@test.com", resultado.get(0).getCorreoUsuario());
        assertEquals("Teclado Mecánico", resultado.get(0).getNombreProducto());

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
        assertEquals(1, resultado.size());
        assertEquals("juan@test.com", resultado.get(0).getCorreoUsuario());

        verify(pedidoRepository, times(1)).findByCorreoUsuario("juan@test.com");
    }

    @Test
    void crearPedidoCorrectamente() {
        // Given
        when(productoClient.obtenerProductoPorId(1L)).thenReturn(productoResponse);
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

        verify(productoClient, times(1)).obtenerProductoPorId(1L);
        verify(pedidoRepository, times(1)).save(any(Pedido.class));
    }

    @Test
    void crearPedidoCuandoProductoNoExisteLanzaExcepcion() {
        // Given
        when(productoClient.obtenerProductoPorId(1L)).thenReturn(null);

        // When / Then
        assertThrows(RuntimeException.class,
                () -> pedidoService.crear(request));

        verify(productoClient, times(1)).obtenerProductoPorId(1L);
        verify(pedidoRepository, never()).save(any(Pedido.class));
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

        verify(pedidoRepository, times(1)).findById(1L);
        verify(pedidoRepository, times(1)).save(pedido);
    }
}