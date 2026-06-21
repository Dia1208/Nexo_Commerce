/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  org.springframework.data.jpa.repository.JpaRepository
 */
package com.nexocommerce.servicio_usuarios.repository;

import com.nexocommerce.servicio_usuarios.model.UsuarioModel;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository
extends JpaRepository<UsuarioModel, Long> {
    public Optional<UsuarioModel> findByCorreo(String var1);
}
