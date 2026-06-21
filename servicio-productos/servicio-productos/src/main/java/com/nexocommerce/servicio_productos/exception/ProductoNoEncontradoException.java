/*
 * Decompiled with CFR 0.151.
 */
package com.nexocommerce.servicio_productos.exception;

public class ProductoNoEncontradoException
extends RuntimeException {
    public ProductoNoEncontradoException(Long id) {
        super("Producto no encontrado con id: " + id);
    }
}
