/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  org.springframework.http.HttpStatus
 *  org.springframework.http.HttpStatusCode
 *  org.springframework.http.ResponseEntity
 *  org.springframework.validation.FieldError
 *  org.springframework.web.bind.MethodArgumentNotValidException
 *  org.springframework.web.bind.annotation.ExceptionHandler
 *  org.springframework.web.bind.annotation.RestControllerAdvice
 */
package com.nexocommerce.servicio_productos.exception;

import com.nexocommerce.servicio_productos.dto.ErrorResponse;
import com.nexocommerce.servicio_productos.exception.ProductoNoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value={ProductoNoEncontradoException.class})
    public ResponseEntity<ErrorResponse> productoNoEncontrado(ProductoNoEncontradoException ex) {
        return ResponseEntity.status((HttpStatusCode)HttpStatus.NOT_FOUND).body((Object)new ErrorResponse("PRODUCTO_NO_ENCONTRADO", ex.getMessage()));
    }

    @ExceptionHandler(value={MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponse> validacion(MethodArgumentNotValidException ex) {
        String mensaje = ((FieldError)ex.getBindingResult().getFieldErrors().get(0)).getDefaultMessage();
        return ResponseEntity.status((HttpStatusCode)HttpStatus.BAD_REQUEST).body((Object)new ErrorResponse("VALIDACION_ERROR", mensaje));
    }

    @ExceptionHandler(value={Exception.class})
    public ResponseEntity<ErrorResponse> errorGeneral(Exception ex) {
        return ResponseEntity.status((HttpStatusCode)HttpStatus.INTERNAL_SERVER_ERROR).body((Object)new ErrorResponse("ERROR_INTERNO", "Ocurri\u00f3 un error interno"));
    }
}
