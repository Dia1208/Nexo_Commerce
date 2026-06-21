/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.stereotype.Service
 */
package com.nexocommerce.servicio_productos.service;

import com.nexocommerce.servicio_productos.dto.ProductoRequest;
import com.nexocommerce.servicio_productos.dto.ProductoResponse;
import com.nexocommerce.servicio_productos.entity.Producto;
import com.nexocommerce.servicio_productos.exception.ProductoNoEncontradoException;
import com.nexocommerce.servicio_productos.repository.ProductoRepository;
import java.util.List;
import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {
    @Generated
    private static final Logger log = LoggerFactory.getLogger(ProductoService.class);
    private final ProductoRepository productoRepository;

    public List<ProductoResponse> listar() {
        return this.productoRepository.findAll().stream().map(this::mapearAResponse).toList();
    }

    public ProductoResponse buscarPorId(Long id) {
        Producto producto = (Producto)this.productoRepository.findById(id).orElseThrow(() -> new ProductoNoEncontradoException(id));
        return this.mapearAResponse(producto);
    }

    public ProductoResponse crear(ProductoRequest request) {
        Producto producto = Producto.builder().nombre(request.getNombre()).descripcion(request.getDescripcion()).precio(request.getPrecio()).stock(request.getStock()).categoria(request.getCategoria()).build();
        Producto productoGuardado = (Producto)this.productoRepository.save(producto);
        log.info("Producto creado con id: {}", (Object)productoGuardado.getId());
        return this.mapearAResponse(productoGuardado);
    }

    public ProductoResponse actualizar(Long id, ProductoRequest request) {
        Producto producto = (Producto)this.productoRepository.findById(id).orElseThrow(() -> new ProductoNoEncontradoException(id));
        producto.setNombre(request.getNombre());
        producto.setDescripcion(request.getDescripcion());
        producto.setPrecio(request.getPrecio());
        producto.setStock(request.getStock());
        producto.setCategoria(request.getCategoria());
        Producto productoActualizado = (Producto)this.productoRepository.save(producto);
        log.info("Producto actualizado con id: {}", (Object)id);
        return this.mapearAResponse(productoActualizado);
    }

    public void eliminar(Long id) {
        Producto producto = (Producto)this.productoRepository.findById(id).orElseThrow(() -> new ProductoNoEncontradoException(id));
        this.productoRepository.delete(producto);
        log.info("Producto eliminado con id: {}", (Object)id);
    }

    private ProductoResponse mapearAResponse(Producto producto) {
        return new ProductoResponse(producto.getId(), producto.getNombre(), producto.getDescripcion(), producto.getPrecio(), producto.getStock(), producto.getCategoria());
    }

    @Generated
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }
}
