package com.nexocommerce.servicio_usuarios.exception;

import com.nexocommerce.servicio_usuarios.dto.ErrorResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

/*
 * Esta clase maneja las excepciones globales del microservicio de usuarios.
 * Permite devolver respuestas claras cuando un usuario no existe,
 * cuando faltan datos obligatorios, cuando el correo ya está registrado
 * o cuando el JSON enviado no tiene el formato correcto.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /*
     * Maneja errores de tipo RuntimeException.
     * Actualmente se usa para casos como "Usuario no encontrado".
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
     * Se ejecuta cuando UsuarioModel no cumple con anotaciones como:
     * @NotBlank o @Email.
     *
     * Para que funcione, el controller debe tener @Valid en el @RequestBody.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> manejarValidaciones(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new LinkedHashMap<>();

        // Recorre todos los campos inválidos y guarda sus mensajes.
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errores.put(error.getField(), error.getDefaultMessage())
        );

        // Respuesta personalizada con todos los campos que deben corregirse.
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("mensaje", "Error de validación");
        response.put("estado", HttpStatus.BAD_REQUEST.value());
        response.put("camposRequeridos", errores);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /*
     * Maneja errores de integridad en la base de datos.
     * Por ejemplo, cuando se intenta registrar un correo que ya existe.
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> manejarDuplicados(DataIntegrityViolationException ex) {
        ErrorResponse error = new ErrorResponse(
                "El correo ya está registrado",
                HttpStatus.BAD_REQUEST.value()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    /*
     * Maneja errores cuando el JSON está mal escrito.
     * Por ejemplo:
     * - Falta una llave.
     * - Hay una coma mal puesta.
     * - Se envía un tipo de dato incorrecto.
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> manejarJsonInvalido(HttpMessageNotReadableException ex) {
        Map<String, Object> response = new LinkedHashMap<>();

        response.put("mensaje", "El JSON enviado no es válido. Revisa los nombres de los campos.");
        response.put("estado", HttpStatus.BAD_REQUEST.value());

        // Ejemplo de JSON correcto para crear o actualizar un usuario.
        response.put("ejemploCorrecto", Map.of(
                "nombre", "Juan Prueba",
                "correo", "juan@test.com",
                "telefono", "123456789",
                "direccion", "Calle 123",
                "rol", "CLIENTE"
        ));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /*
     * Maneja cualquier otro error inesperado.
     * Evita devolver información técnica interna al cliente.
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