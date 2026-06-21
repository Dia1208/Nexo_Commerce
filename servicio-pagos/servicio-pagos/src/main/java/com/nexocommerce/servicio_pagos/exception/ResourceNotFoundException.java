package com.nexocommerce.servicio_pagos.exception;

/*
 * Excepción usada cuando no se encuentra un recurso.
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String mensaje) {
        super(mensaje);
    }
}