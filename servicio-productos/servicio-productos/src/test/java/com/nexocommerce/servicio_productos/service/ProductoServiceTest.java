package com.nexocommerce.servicio_productos.service;

import com.nexocommerce.servicio_productos.dto.ProductoRequest;
import com.nexocommerce.servicio_productos.dto.ProductoResponse;
import com.nexocommerce.servicio_productos.entity.Producto;
import com.nexocommerce.servicio_productos.exception.ProductoNoEncontradoException;
import com.nexocommerce.servicio_productos.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/*
 * Pruebas unitarias del servicio de productos.
 * Se prueban los métodos principales del CRUD.
 */
@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    private Producto producto;
    private ProductoRequest request;

    @BeforeEach
    void setUp() {
        producto = Producto.builder()
                .id(1L)
                .nombre("Teclado Mecánico")
                .descripcion("Teclado gamer")
                .precio(new BigDecimal("45990"))
                .stock(10)
                .categoria("Tecnología")
                .build();

        request = new ProductoRequest();
        request.setNombre("Teclado Mecánico");
        request.setDescripcion("Teclado gamer");
        request.setPrecio(new BigDecimal("45990"));
        request.setStock(10);
        request.setCategoria("Tecnología");
    }

    @Test
    void listarProductosCorrectamente() {
        when(productoRepository.findAll()).thenReturn(List.of(producto));

        List<ProductoResponse> resultado = productoService.listar();

        assertEquals(1, resultado.size());
        assertEquals("Teclado Mecánico", resultado.get(0).getNombre());
    }

    @Test
    void buscarProductoPorIdCorrectamente() {
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        ProductoResponse response = productoService.buscarPorId(1L);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Teclado Mecánico", response.getNombre());
    }

    @Test
    void buscarProductoNoExistenteLanzaExcepcion() {
        when(productoRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ProductoNoEncontradoException.class,
                () -> productoService.buscarPorId(99L));
    }

    @Test
    void crearProductoCorrectamente() {
        when(productoRepository.save(any(Producto.class))).thenReturn(producto);

        ProductoResponse response = productoService.crear(request);

        assertNotNull(response);
        assertEquals("Teclado Mecánico", response.getNombre());

        verify(productoRepository).save(any(Producto.class));
    }

    @Test
    void actualizarProductoCorrectamente() {
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));
        when(productoRepository.save(any(Producto.class))).thenReturn(producto);

        ProductoResponse response = productoService.actualizar(1L, request);

        assertNotNull(response);
        assertEquals("Teclado Mecánico", response.getNombre());

        verify(productoRepository).save(producto);
    }

    @Test
    void eliminarProductoCorrectamente() {
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        productoService.eliminar(1L);

        verify(productoRepository).delete(producto);
    }
}