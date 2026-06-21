package com.nexocommerce.servicio_pagos.exception;

import com.nexocommerce.servicio_pagos.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

/*
 * Esta clase maneja las excepciones globales del microservicio de pagos.
 * Permite devolver respuestas claras cuando un pago no existe,
 * cuando faltan datos obligatorios o cuando el JSON enviado no es válido.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /*
     * Maneja el error cuando no se encuentra un recurso.
     * Por ejemplo, cuando se busca, aprueba o rechaza un pago con un ID inexistente.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> manejarResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    /*
     * Maneja errores de validación.
     * Se ejecuta cuando PagoRequest no cumple con sus validaciones,
     * por ejemplo si falta el monto, el pedidoId o el método de pago.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> manejarValidaciones(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new LinkedHashMap<>();

        // Guarda cada campo inválido junto con su mensaje personalizado.
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errores.put(error.getField(), error.getDefaultMessage())
        );

        // Respuesta personalizada con todos los errores de validación.
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("mensaje", "Error de validación");
        response.put("estado", HttpStatus.BAD_REQUEST.value());
        response.put("camposRequeridos", errores);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /*
     * Maneja errores de JSON inválido.
     * También se ejecuta si el usuario manda un enum incorrecto,
     * por ejemplo "TARJETA_CREDITO" cuando el sistema espera "TARJETA".
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> manejarJsonInvalido(HttpMessageNotReadableException ex) {
        Map<String, Object> response = new LinkedHashMap<>();

        response.put("mensaje", "El JSON enviado no es válido. Revisa los nombres de los campos y los valores permitidos.");
        response.put("estado", HttpStatus.BAD_REQUEST.value());

        // Ejemplo de JSON correcto para crear un pago.
        response.put("ejemploCorrecto", Map.of(
                "pedidoId", 1,
                "correoUsuario", "juan@test.com",
                "monto", 91980,
                "metodoPago", "TARJETA"
        ));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /*
     * Maneja cualquier otro error inesperado.
     * Permite devolver una respuesta simple sin mostrar detalles internos.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> manejarExcepcionGeneral(Exception ex) {
        ErrorResponse error = new ErrorResponse(
                "Error interno del servidor",
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}