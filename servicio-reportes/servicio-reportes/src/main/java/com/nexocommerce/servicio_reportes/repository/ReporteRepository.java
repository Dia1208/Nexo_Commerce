package com.nexocommerce.servicio_reportes.repository;

import com.nexocommerce.servicio_reportes.entity.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 * Repositorio encargado de las operaciones CRUD
 * sobre la entidad Reporte.
 */
public interface ReporteRepository extends JpaRepository<Reporte, Long> {
}