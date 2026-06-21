package com.nexocommerce.servicio_usuarios.controller;

import com.nexocommerce.servicio_usuarios.model.UsuarioModel;
import com.nexocommerce.servicio_usuarios.service.UsuarioService;
import lombok.RequiredArgsConstructor;
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

    // Lista todos los usuarios registrados.
    @GetMapping("/listar")
    public ResponseEntity<List<UsuarioModel>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    // Busca un usuario por su ID.
    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscar(id));
    }

    // Crea un nuevo usuario.
    @PostMapping("/crear")
    public ResponseEntity<?> crear(@RequestBody UsuarioModel usuario) {
        return ResponseEntity.ok(service.guardar(usuario));
    }

    // Actualiza un usuario existente.
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody UsuarioModel usuario) {
        return ResponseEntity.ok(service.actualizar(id, usuario));
    }

    // Elimina un usuario por ID.
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok("Usuario eliminado correctamente");
    }
}