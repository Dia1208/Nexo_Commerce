package com.nexocommerce.servicio_reportes.exception;

import com.nexocommerce.servicio_reportes.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

/*
 * Esta clase maneja las excepciones globales del microservicio de reportes.
 * Permite devolver respuestas de error claras cuando ocurre un problema,
 * por ejemplo cuando un reporte no existe, cuando faltan datos obligatorios
 * o cuando el JSON enviado tiene un formato incorrecto.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /*
     * Maneja el error cuando no se encuentra un recurso.
     * Por ejemplo, cuando se busca un reporte por un ID que no existe.
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
     * Se ejecuta cuando ReporteRequest no cumple con anotaciones como:
     * @NotBlank, @NotNull, @Min o @DecimalMin.
     *
     * En vez de devolver solo un mensaje general,
     * devuelve todos los campos que tienen errores.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> manejarValidaciones(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new LinkedHashMap<>();

        // Recorre todos los errores de validación encontrados.
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errores.put(error.getField(), error.getDefaultMessage())
        );

        // Respuesta personalizada para mostrar los campos que deben corregirse.
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("mensaje", "Error de validación");
        response.put("estado", HttpStatus.BAD_REQUEST.value());
        response.put("camposRequeridos", errores);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /*
     * Maneja errores cuando el JSON viene mal escrito.
     * Por ejemplo:
     * - Falta una coma.
     * - Se envía un tipo de dato incorrecto.
     * - Se escribe un valor inválido para un enum, como "VENTA" en vez de "VENTAS".
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> manejarJsonInvalido(HttpMessageNotReadableException ex) {
        Map<String, Object> response = new LinkedHashMap<>();

        response.put("mensaje", "El JSON enviado no es válido. Revisa los nombres de los campos y los valores permitidos.");
        response.put("estado", HttpStatus.BAD_REQUEST.value());

        // Ejemplo de JSON correcto para crear un reporte.
        response.put("ejemploCorrecto", Map.of(
                "nombre", "Reporte general de ventas",
                "tipo", "VENTAS",
                "cantidadPedidos", 10,
                "cantidadProductos", 25,
                "totalVentas", 350000
        ));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /*
     * Maneja cualquier otro error inesperado.
     * Evita que el microservicio devuelva errores técnicos difíciles de entender.
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