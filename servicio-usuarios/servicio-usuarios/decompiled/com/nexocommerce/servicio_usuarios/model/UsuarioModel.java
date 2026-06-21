/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  com.nexocommerce.servicio_usuarios.model.UsuarioModel$UsuarioModelBuilder
 *  jakarta.persistence.Column
 *  jakarta.persistence.Entity
 *  jakarta.persistence.GeneratedValue
 *  jakarta.persistence.GenerationType
 *  jakarta.persistence.Id
 *  jakarta.persistence.Table
 *  lombok.Generated
 */
package com.nexocommerce.servicio_usuarios.model;

import com.nexocommerce.servicio_usuarios.model.UsuarioModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Generated;

@Entity
@Table(name="usuarios")
public class UsuarioModel {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @Column(unique=true)
    private String correo;
    private String telefono;
    private String direccion;
    private String rol;

    @Generated
    public static UsuarioModelBuilder builder() {
        return new UsuarioModelBuilder();
    }

    @Generated
    public Long getId() {
        return this.id;
    }

    @Generated
    public String getNombre() {
        return this.nombre;
    }

    @Generated
    public String getCorreo() {
        return this.correo;
    }

    @Generated
    public String getTelefono() {
        return this.telefono;
    }

    @Generated
    public String getDireccion() {
        return this.direccion;
    }

    @Generated
    public String getRol() {
        return this.rol;
    }

    @Generated
    public void setId(Long id) {
        this.id = id;
    }

    @Generated
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Generated
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Generated
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Generated
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Generated
    public void setRol(String rol) {
        this.rol = rol;
    }

    @Generated
    public UsuarioModel() {
    }

    @Generated
    public UsuarioModel(Long id, String nombre, String correo, String telefono, String direccion, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.direccion = direccion;
        this.rol = rol;
    }
}
