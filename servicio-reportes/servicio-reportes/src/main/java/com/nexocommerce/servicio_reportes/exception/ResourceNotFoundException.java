package com.nexocommerce.servicio_reportes.exception;

/*
 * Excepción usada cuando no se encuentra un recurso.
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String mensaje) {
        super(mensaje);
    }
}