package com.nexocommerce.servicio_reportes.exception;

/*
 * Excepción personalizada que se lanza cuando
 * no se encuentra un reporte con el ID indicado.
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Long id) {
        super("Reporte no encontrado con id: " + id);
    }
}