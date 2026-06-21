package com.nexocommerce.servicio_pedidos.repository;

import com.nexocommerce.servicio_pedidos.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*
 * Repositorio encargado de las operaciones CRUD
 * sobre la entidad Pedido.
 */
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    // Busca todos los pedidos realizados por un usuario según su correo.
    List<Pedido> findByCorreoUsuario(String correoUsuario);
}