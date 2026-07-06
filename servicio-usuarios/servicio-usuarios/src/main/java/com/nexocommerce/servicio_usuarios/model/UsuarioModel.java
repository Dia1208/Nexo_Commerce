package com.nexocommerce.servicio_usuarios.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/*
 * Esta clase representa la información de un usuario dentro del sistema.
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
    @NotBlank(message = "El nombre del usuario es obligatorio")
    @Column(nullable = false)
    private String nombre;

    // Correo del usuario. Debe ser único en la base de datos.
    @NotBlank(message = "El correo del usuario es obligatorio")
    @Email(message = "El correo debe tener un formato válido")
    @Column(nullable = false, unique = true)
    private String correo;

    // Teléfono del usuario.
    private String telefono;

    // Dirección del usuario.
    private String direccion;

    // Rol del usuario dentro del sistema. Solo permite CLIENTE o ADMIN.
    @NotNull(message = "El rol del usuario es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RolUsuario rol;
}