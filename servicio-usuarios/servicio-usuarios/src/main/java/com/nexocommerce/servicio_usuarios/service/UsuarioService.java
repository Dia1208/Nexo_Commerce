package com.nexocommerce.servicio_usuarios.service;

import com.nexocommerce.servicio_usuarios.model.UsuarioModel;
import com.nexocommerce.servicio_usuarios.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * Servicio encargado de la lógica de negocio relacionada con usuarios.
 */
@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;

    // Lista todos los usuarios registrados.
    public List<UsuarioModel> listar() {
        return repository.findAll();
    }

    // Busca un usuario por su identificador.
    public UsuarioModel buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    // Guarda un nuevo usuario.
    public UsuarioModel guardar(UsuarioModel usuario) {
        return repository.save(usuario);
    }

    // Actualiza la información de un usuario existente.
    public UsuarioModel actualizar(Long id, UsuarioModel usuario) {
        UsuarioModel existente = buscar(id);

        existente.setNombre(usuario.getNombre());
        existente.setCorreo(usuario.getCorreo());
        existente.setTelefono(usuario.getTelefono());
        existente.setDireccion(usuario.getDireccion());
        existente.setRol(usuario.getRol());

        return repository.save(existente);
    }

    // Elimina un usuario por su identificador.
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}