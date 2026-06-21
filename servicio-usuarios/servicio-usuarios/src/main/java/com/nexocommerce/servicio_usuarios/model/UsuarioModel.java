package com.nexocommerce.servicio_usuarios.model;

import jakarta.persistence.*;
import lombok.*;

/*
 * Esta clase representa la información del usuario dentro del sistema.
 * Guarda datos básicos como nombre, correo, teléfono, dirección y rol.
 */
@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioModel {

    // Identificador único del usuario.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre del usuario.
    @Column(nullable = false)
    private String nombre;

    // Correo del usuario.
    @Column(nullable = false, unique = true)
    private String correo;

    // Teléfono del usuario.
    private String telefono;

    // Dirección del usuario.
    private String direccion;

    // Rol del usuario dentro del sistema.
    private String rol;
}