/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  org.springframework.data.jpa.repository.JpaRepository
 */
package com.nexocommerce.servicio_productos.repository;

import com.nexocommerce.servicio_productos.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository
extends JpaRepository<Producto, Long> {
}
