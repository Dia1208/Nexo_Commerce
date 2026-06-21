package com.nexocommerce.servicio_pagos.repository;

import com.nexocommerce.servicio_pagos.entity.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*
 * Esta interfaz permite realizar operaciones CRUD sobre pagos.
 * Spring Data JPA genera automáticamente los métodos básicos.
 */
public interface PagoRepository extends JpaRepository<Pago, Long> {

    // Busca pagos asociados a un pedido específico por id.
    List<Pago> findByPedidoId(Long pedidoId);

    // Busca pagos asociados a un usuario específico por correo.
    List<Pago> findByCorreoUsuario(String correoUsuario);
}