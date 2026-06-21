/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package com.nexocommerce.servicio_autenticacion.entity;

import com.nexocommerce.servicio_autenticacion.entity.UsuarioAuth;
import lombok.Generated;

@Generated
public static class UsuarioAuth.UsuarioAuthBuilder {
    @Generated
    private Long id;
    @Generated
    private String nombre;
    @Generated
    private String correo;
    @Generated
    private String password;
    @Generated
    private String rol;

    @Generated
    UsuarioAuth.UsuarioAuthBuilder() {
    }

    @Generated
    public UsuarioAuth.UsuarioAuthBuilder id(Long id) {
        this.id = id;
        return this;
    }

    @Generated
    public UsuarioAuth.UsuarioAuthBuilder nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    @Generated
    public UsuarioAuth.UsuarioAuthBuilder correo(String correo) {
        this.correo = correo;
        return this;
    }

    @Generated
    public UsuarioAuth.UsuarioAuthBuilder password(String password) {
        this.password = password;
        return this;
    }

    @Generated
    public UsuarioAuth.UsuarioAuthBuilder rol(String rol) {
        this.rol = rol;
        return this;
    }

    @Generated
    public UsuarioAuth build() {
        return new UsuarioAuth(this.id, this.nombre, this.correo, this.password, this.rol);
    }

    @Generated
    public String toString() {
        return "UsuarioAuth.UsuarioAuthBuilder(id=" + this.id + ", nombre=" + this.nombre + ", correo=" + this.correo + ", password=" + this.password + ", rol=" + this.rol + ")";
    }
}
