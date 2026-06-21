package com.nexocommerce.servicio_productos.service;

import com.nexocommerce.servicio_productos.dto.ProductoRequest;
import com.nexocommerce.servicio_productos.dto.ProductoResponse;
import com.nexocommerce.servicio_productos.entity.Producto;
import com.nexocommerce.servicio_productos.exception.ProductoNoEncontradoException;
import com.nexocommerce.servicio_productos.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * Servicio encargado de la lógica de negocio relacionada con productos.
 */
@Service

/*
 * Genera automáticamente el constructor para inyectar
 * el repositorio ProductoRepository en este servicio.
 */
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;

    public List<ProductoResponse> listar() {
        return productoRepository.findAll()
                .stream()
                .map(this::mapearAResponse)
                .toList();
    }

    public ProductoResponse buscarPorId(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNoEncontradoException(id));

        return mapearAResponse(producto);
    }

    public ProductoResponse crear(ProductoRequest request) {
        Producto producto = Producto.builder()
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .precio(request.getPrecio())
                .stock(request.getStock())
                .categoria(request.getCategoria())
                .build();

        Producto productoGuardado = productoRepository.save(producto);

        return mapearAResponse(productoGuardado);
    }

    public ProductoResponse actualizar(Long id, ProductoRequest request) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNoEncontradoException(id));

        producto.setNombre(request.getNombre());
        producto.setDescripcion(request.getDescripcion());
        producto.setPrecio(request.getPrecio());
        producto.setStock(request.getStock());
        producto.setCategoria(request.getCategoria());

        Producto productoActualizado = productoRepository.save(producto);

        return mapearAResponse(productoActualizado);
    }

    public void eliminar(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNoEncontradoException(id));

        productoRepository.delete(producto);
    }

    private ProductoResponse mapearAResponse(Producto producto) {
        return new ProductoResponse(
                producto.getId(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.getStock(),
                producto.getCategoria()
        );
    }
}