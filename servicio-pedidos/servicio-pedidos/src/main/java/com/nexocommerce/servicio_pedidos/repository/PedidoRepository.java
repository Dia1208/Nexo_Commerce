/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  org.springframework.data.jpa.repository.JpaRepository
 */
package com.nexocommerce.servicio_pedidos.repository;

import com.nexocommerce.servicio_pedidos.entity.Pedido;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository
extends JpaRepository<Pedido, Long> {
    public List<Pedido> findByCorreoUsuario(String var1);
}
