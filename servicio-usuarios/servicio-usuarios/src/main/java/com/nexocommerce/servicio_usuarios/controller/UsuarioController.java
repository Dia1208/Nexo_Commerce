/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  org.springframework.http.ResponseEntity
 *  org.springframework.web.bind.annotation.DeleteMapping
 *  org.springframework.web.bind.annotation.GetMapping
 *  org.springframework.web.bind.annotation.PathVariable
 *  org.springframework.web.bind.annotation.PostMapping
 *  org.springframework.web.bind.annotation.PutMapping
 *  org.springframework.web.bind.annotation.RequestBody
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RestController
 */
package com.nexocommerce.servicio_usuarios.controller;

import com.nexocommerce.servicio_usuarios.model.UsuarioModel;
import com.nexocommerce.servicio_usuarios.service.UsuarioService;
import java.util.List;
import lombok.Generated;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/api/usuarios"})
public class UsuarioController {
    private final UsuarioService service;

    @GetMapping(value={"/listar"})
    public ResponseEntity<List<UsuarioModel>> listar() {
        return ResponseEntity.ok(this.service.listar());
    }

    @GetMapping(value={"/{id}"})
    public ResponseEntity<UsuarioModel> buscar(@PathVariable Long id) {
        return ResponseEntity.ok((Object)this.service.buscar(id));
    }

    @PostMapping(value={"/crear"})
    public ResponseEntity<UsuarioModel> crear(@RequestBody UsuarioModel usuario) {
        return ResponseEntity.ok((Object)this.service.guardar(usuario));
    }

    @PutMapping(value={"/{id}"})
    public ResponseEntity<UsuarioModel> actualizar(@PathVariable Long id, @RequestBody UsuarioModel usuario) {
        return ResponseEntity.ok((Object)this.service.actualizar(id, usuario));
    }

    @DeleteMapping(value={"/{id}"})
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        this.service.eliminar(id);
        return ResponseEntity.ok((Object)"Usuario eliminado correctamente");
    }

    @Generated
    public UsuarioController(UsuarioService service) {
        this.service = service;
    }
}
