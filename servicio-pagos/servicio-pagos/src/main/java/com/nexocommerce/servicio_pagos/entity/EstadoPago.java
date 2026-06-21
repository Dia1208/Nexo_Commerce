package com.nexocommerce.servicio_pagos.entity;

/*
 * Enum que define los estados posibles de un pago.
 * Se usa para evitar escribir estados incorrectos como texto libre.
 */
public enum EstadoPago {
    PENDIENTE,
    APROBADO,
    RECHAZADO
}