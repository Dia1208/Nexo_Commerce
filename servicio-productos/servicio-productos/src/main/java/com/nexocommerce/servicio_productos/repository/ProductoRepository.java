package com.nexocommerce.servicio_productos.repository;

import com.nexocommerce.servicio_productos.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 * Repositorio encargado de las operaciones CRUD
 * sobre la entidad Producto.
 */
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}