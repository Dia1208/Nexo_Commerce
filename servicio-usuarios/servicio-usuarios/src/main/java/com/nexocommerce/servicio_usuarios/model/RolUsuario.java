package com.nexocommerce.servicio_usuarios.model;

/*
 * Enum que define los roles permitidos para los usuarios.
 * Evita que se guarden roles inválidos como VENDEDOR, PRUEBA, etc.
 */
public enum RolUsuario {
    CLIENTE,
    ADMIN
}