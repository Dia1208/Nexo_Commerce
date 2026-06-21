package com.nexocommerce.servicio_productos.exception;

import com.nexocommerce.servicio_productos.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

/*
 * Esta clase maneja las excepciones globales del microservicio de productos.
 * Permite devolver respuestas claras cuando ocurre un error,
 * por ejemplo cuando un producto no existe, cuando faltan datos
 * o cuando el JSON enviado no tiene el formato correcto.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /*
     * Maneja el error cuando no se encuentra un producto.
     * Por ejemplo, cuando se busca, actualiza o elimina un producto con un ID inexistente.
     */
    @ExceptionHandler(ProductoNoEncontradoException.class)
    public ResponseEntity<?> manejarProductoNoEncontrado(ProductoNoEncontradoException ex) {
        ErrorResponse error = new ErrorResponse(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    /*
     * Maneja errores de validación.
     * Se ejecuta cuando ProductoRequest no cumple con reglas como:
     * @NotBlank, @NotNull, @Min o @DecimalMin.
     *
     * Devuelve todos los campos inválidos en la respuesta.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> manejarValidaciones(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new LinkedHashMap<>();

        // Guarda cada campo inválido junto con su mensaje personalizado.
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
     * Maneja errores cuando el JSON está mal formado.
     * También ayuda cuando se envía un tipo de dato incorrecto.
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> manejarJsonInvalido(HttpMessageNotReadableException ex) {
        Map<String, Object> response = new LinkedHashMap<>();

        response.put("mensaje", "El JSON enviado no es válido. Revisa los nombres de los campos y los valores permitidos.");
        response.put("estado", HttpStatus.BAD_REQUEST.value());

        // Ejemplo de cómo debe enviarse correctamente un producto.
        response.put("ejemploCorrecto", Map.of(
                "nombre", "Teclado Mecánico",
                "descripcion", "Teclado gamer RGB",
                "precio", 45990,
                "stock", 10,
                "categoria", "Tecnología"
        ));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /*
     * Maneja cualquier otro error inesperado.
     * Evita mostrar detalles internos del sistema al cliente.
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