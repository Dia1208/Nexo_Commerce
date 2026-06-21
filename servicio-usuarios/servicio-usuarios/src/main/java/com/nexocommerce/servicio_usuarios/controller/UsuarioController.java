package com.nexocommerce.servicio_usuarios.controller;

import com.nexocommerce.servicio_usuarios.model.UsuarioModel;
import com.nexocommerce.servicio_usuarios.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * Controlador REST del microservicio de usuarios.
 * Expone endpoints para listar, buscar, crear,
 * actualizar y eliminar usuarios.
 */
@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    /*
     * Lista todos los usuarios registrados.
     * Endpoint: GET /api/usuarios/listar
     */
    @GetMapping("/listar")
    public ResponseEntity<List<UsuarioModel>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    /*
     * Busca un usuario por su ID.
     * Endpoint: GET /api/usuarios/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscar(id));
    }

    /*
     * Busca un usuario por su correo.
     * Endpoint: GET /api/usuarios/correo/{correo}
     */
    @GetMapping("/correo/{correo}")
    public ResponseEntity<?> buscarPorCorreo(@PathVariable String correo) {
        return ResponseEntity.ok(service.buscarPorCorreo(correo));
    }

    /*
     * Crea un nuevo usuario.
     * Endpoint: POST /api/usuarios/crear
     */
    @PostMapping("/crear")
    public ResponseEntity<?> crear(@Valid @RequestBody UsuarioModel usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(usuario));
    }

    /*
     * Actualiza un usuario existente.
     * Endpoint: PUT /api/usuarios/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioModel usuario) {
        return ResponseEntity.ok(service.actualizar(id, usuario));
    }

    /*
     * Elimina un usuario por ID.
     * Endpoint: DELETE /api/usuarios/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok("Usuario eliminado correctamente");
    }
}