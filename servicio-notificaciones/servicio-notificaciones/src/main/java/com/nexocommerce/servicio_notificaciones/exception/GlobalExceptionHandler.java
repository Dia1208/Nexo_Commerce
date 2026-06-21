package com.nexocommerce.servicio_notificaciones.exception;

import com.nexocommerce.servicio_notificaciones.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

/*
 * Esta clase maneja las excepciones globales del microservicio de notificaciones.
 * Permite devolver mensajes claros cuando una notificación no existe,
 * cuando faltan datos obligatorios o cuando se envía un tipo de notificación incorrecto.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /*
     * Maneja el error cuando no se encuentra un recurso.
     * Por ejemplo, cuando se busca o actualiza una notificación con un ID inexistente.
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
     * Se ejecuta cuando NotificacionRequest no cumple con sus validaciones,
     * por ejemplo si falta el correo, el título, el mensaje o el tipo.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> manejarValidaciones(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new LinkedHashMap<>();

        // Guarda todos los campos que fallaron la validación.
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errores.put(error.getField(), error.getDefaultMessage())
        );

        // Respuesta personalizada con los campos requeridos o inválidos.
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("mensaje", "Error de validación");
        response.put("estado", HttpStatus.BAD_REQUEST.value());
        response.put("camposRequeridos", errores);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /*
     * Maneja errores cuando el JSON tiene un formato incorrecto.
     * También se ejecuta si el usuario envía un tipo de notificación no permitido.
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> manejarJsonInvalido(HttpMessageNotReadableException ex) {
        Map<String, Object> response = new LinkedHashMap<>();

        response.put("mensaje", "El JSON enviado no es válido. Revisa los nombres de los campos y los valores permitidos.");
        response.put("estado", HttpStatus.BAD_REQUEST.value());

        // Ejemplo de JSON correcto para crear una notificación.
        response.put("ejemploCorrecto", Map.of(
                "correoUsuario", "juan@test.com",
                "titulo", "Pedido creado",
                "mensaje", "Tu pedido fue creado correctamente",
                "tipo", "PEDIDO"
        ));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /*
     * Maneja cualquier otro error inesperado.
     * Evita devolver errores técnicos difíciles de entender.
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