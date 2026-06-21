package com.nexocommerce.servicio_usuarios.service;

import com.nexocommerce.servicio_usuarios.model.UsuarioModel;
import com.nexocommerce.servicio_usuarios.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * Servicio encargado de la lógica de negocio relacionada con usuarios.
 * Aquí se procesan las operaciones antes de guardar o consultar datos en la base de datos.
 */
@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;

    /*
     * Lista todos los usuarios registrados.
     */
    public List<UsuarioModel> listar() {
        return repository.findAll();
    }

    /*
     * Busca un usuario por su identificador.
     * Si no existe, lanza una excepción.
     */
    public UsuarioModel buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
    }

    /*
     * Busca un usuario por su correo.
     * Si no existe, lanza una excepción.
     */
    public UsuarioModel buscarPorCorreo(String correo) {
        return repository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con correo: " + correo));
    }

    /*
     * Guarda un nuevo usuario.
     * Antes valida que el correo no esté registrado.
     */
    public UsuarioModel guardar(UsuarioModel usuario) {
        if (repository.existsByCorreo(usuario.getCorreo())) {
            throw new RuntimeException("El correo ya está registrado");
        }

        return repository.save(usuario);
    }

    /*
     * Actualiza la información de un usuario existente.
     * Primero busca el usuario por ID y luego reemplaza sus datos.
     */
    public UsuarioModel actualizar(Long id, UsuarioModel usuario) {
        UsuarioModel existente = buscar(id);

        /*
         * Si el correo cambió, se valida que el nuevo correo
         * no esté registrado por otro usuario.
         */
        if (!existente.getCorreo().equals(usuario.getCorreo())
                && repository.existsByCorreo(usuario.getCorreo())) {
            throw new RuntimeException("El correo ya está registrado");
        }

        existente.setNombre(usuario.getNombre());
        existente.setCorreo(usuario.getCorreo());
        existente.setTelefono(usuario.getTelefono());
        existente.setDireccion(usuario.getDireccion());
        existente.setRol(usuario.getRol());

        return repository.save(existente);
    }

    /*
     * Elimina un usuario por su identificador.
     * Primero valida que exista.
     */
    public void eliminar(Long id) {
        UsuarioModel existente = buscar(id);
        repository.delete(existente);
    }
}