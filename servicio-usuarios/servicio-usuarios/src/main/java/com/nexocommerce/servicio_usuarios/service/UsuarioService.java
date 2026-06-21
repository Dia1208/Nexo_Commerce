/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  org.springframework.stereotype.Service
 */
package com.nexocommerce.servicio_usuarios.service;

import com.nexocommerce.servicio_usuarios.model.UsuarioModel;
import com.nexocommerce.servicio_usuarios.repository.UsuarioRepository;
import java.util.List;
import lombok.Generated;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;

    public List<UsuarioModel> listar() {
        return this.repository.findAll();
    }

    public UsuarioModel buscar(Long id) {
        return (UsuarioModel)this.repository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public UsuarioModel guardar(UsuarioModel usuario) {
        return (UsuarioModel)this.repository.save(usuario);
    }

    public UsuarioModel actualizar(Long id, UsuarioModel usuario) {
        UsuarioModel existente = this.buscar(id);
        existente.setNombre(usuario.getNombre());
        existente.setCorreo(usuario.getCorreo());
        existente.setTelefono(usuario.getTelefono());
        existente.setDireccion(usuario.getDireccion());
        existente.setRol(usuario.getRol());
        return (UsuarioModel)this.repository.save(existente);
    }

    public void eliminar(Long id) {
        this.repository.deleteById(id);
    }

    @Generated
    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }
}
