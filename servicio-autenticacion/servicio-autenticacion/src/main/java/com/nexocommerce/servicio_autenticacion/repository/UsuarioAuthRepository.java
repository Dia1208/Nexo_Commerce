package com.nexocommerce.servicio_autenticacion.repository;

import com.nexocommerce.servicio_autenticacion.entity.UsuarioAuth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioAuthRepository extends JpaRepository<UsuarioAuth, Long> {

    Optional<UsuarioAuth> findByCorreo(String correo);

    boolean existsByCorreo(String correo);
}