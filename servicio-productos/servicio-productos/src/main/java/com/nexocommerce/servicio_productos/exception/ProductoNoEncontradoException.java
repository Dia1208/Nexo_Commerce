package com.nexocommerce.servicio_productos.exception;

/*
 * Excepción personalizada que se lanza cuando
 * no se encuentra un producto con el ID indicado.
 */
public class ProductoNoEncontradoException extends RuntimeException {

    public ProductoNoEncontradoException(Long id) {
        super("Producto no encontrado con id: " + id);
    }
}