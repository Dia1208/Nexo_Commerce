package com.nexocommerce.servicio_reportes.repository;

import com.nexocommerce.servicio_reportes.entity.Reporte;
import com.nexocommerce.servicio_reportes.entity.TipoReporte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*
 * Repositorio encargado de las operaciones CRUD
 * sobre la entidad Reporte.
 */
public interface ReporteRepository extends JpaRepository<Reporte, Long> {

    // Busca reportes según su tipo.
    List<Reporte> findByTipo(TipoReporte tipo);
}