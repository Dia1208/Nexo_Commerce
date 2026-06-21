/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  jakarta.validation.Valid
 *  lombok.Generated
 *  org.springframework.http.HttpStatus
 *  org.springframework.http.HttpStatusCode
 *  org.springframework.http.ResponseEntity
 *  org.springframework.web.bind.annotation.DeleteMapping
 *  org.springframework.web.bind.annotation.GetMapping
 *  org.springframework.web.bind.annotation.PathVariable
 *  org.springframework.web.bind.annotation.PostMapping
 *  org.springframework.web.bind.annotation.PutMapping
 *  org.springframework.web.bind.annotation.RequestBody
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RestController
 */
package com.nexocommerce.servicio_productos.controller;

import com.nexocommerce.servicio_productos.dto.ProductoRequest;
import com.nexocommerce.servicio_productos.dto.ProductoResponse;
import com.nexocommerce.servicio_productos.service.ProductoService;
import jakarta.validation.Valid;
import lombok.Generated;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/api/productos"})
public class ProductoController {
    private final ProductoService productoService;

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(this.productoService.listar());
    }

    @GetMapping(value={"/{id}"})
    public ResponseEntity<ProductoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok((Object)this.productoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ProductoResponse> crear(@Valid @RequestBody ProductoRequest request) {
        return ResponseEntity.status((HttpStatusCode)HttpStatus.CREATED).body((Object)this.productoService.crear(request));
    }

    @PutMapping(value={"/{id}"})
    public ResponseEntity<ProductoResponse> actualizar(@PathVariable Long id, @Valid @RequestBody ProductoRequest request) {
        return ResponseEntity.ok((Object)this.productoService.actualizar(id, request));
    }

    @DeleteMapping(value={"/{id}"})
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        this.productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @Generated
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }
}
