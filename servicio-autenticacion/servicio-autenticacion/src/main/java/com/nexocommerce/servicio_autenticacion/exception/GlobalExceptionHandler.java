package com.nexocommerce.servicio_autenticacion.exception;

import com.nexocommerce.servicio_autenticacion.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

/*
 * Esta clase maneja las excepciones globales del microservicio de autenticación.
 * Permite devolver respuestas claras cuando ocurre un error durante el registro,
 * el inicio de sesión o cuando los datos enviados no son válidos.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /*
     * Maneja errores de tipo RuntimeException.
     * En este microservicio se usa para errores como:
     * - El correo ya está registrado.
     * - Correo o contraseña incorrectos.
     *
     * En vez de devolver 500, devuelve 400 porque normalmente
     * son errores provocados por datos enviados por el cliente.
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> manejarRuntimeException(RuntimeException ex) {
        ErrorResponse error = new ErrorResponse(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    /*
     * Maneja errores de validación.
     * Se ejecuta cuando RegistroRequest o LoginRequest no cumplen
     * con anotaciones como @NotBlank, @Email, @Size o @NotNull.
     *
     * Devuelve todos los campos que tienen errores para que el cliente
     * sepa exactamente qué debe corregir.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> manejarValidaciones(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new LinkedHashMap<>();

        // Recorre todos los errores de validación encontrados.
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errores.put(error.getField(), error.getDefaultMessage())
        );

        // Respuesta personalizada con todos los campos inválidos.
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("mensaje", "Error de validación");
        response.put("estado", HttpStatus.BAD_REQUEST.value());
        response.put("camposRequeridos", errores);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /*
     * Maneja errores cuando el JSON enviado está mal escrito
     * o no coincide con la estructura esperada.
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> manejarJsonInvalido(HttpMessageNotReadableException ex) {
        Map<String, Object> response = new LinkedHashMap<>();

        response.put("mensaje", "El JSON enviado no es válido. Revisa los nombres de los campos.");
        response.put("estado", HttpStatus.BAD_REQUEST.value());

        // Ejemplo correcto para registrar un usuario.
        response.put("ejemploRegistro", Map.of(
                "nombre", "Juan Prueba",
                "correo", "juan@test.com",
                "password", "123456"
        ));

        // Ejemplo correcto para iniciar sesión.
        response.put("ejemploLogin", Map.of(
                "correo", "juan@test.com",
                "password", "123456"
        ));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /*
     * Maneja cualquier otro error inesperado.
     * Evita mostrar trazas o mensajes técnicos al cliente.
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