package com.nexocommerce.servicio_usuarios.repository;

import com.nexocommerce.servicio_usuarios.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/*
 * Repositorio encargado de las operaciones CRUD
 * sobre la entidad UsuarioModel.
 */
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

    /*
     * Busca un usuario por su correo.
     * Se puede usar para validar si un correo ya existe.
     */
    Optional<UsuarioModel> findByCorreo(String correo);

    /*
     * Verifica si ya existe un usuario con el correo indicado.
     */
    boolean existsByCorreo(String correo);
}