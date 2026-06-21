package com.nexocommerce.servicio_reportes.repository;

import com.nexocommerce.servicio_reportes.entity.Reporte;
import com.nexocommerce.servicio_reportes.entity.TipoReporte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*
 * Esta interfaz permite realizar operaciones CRUD sobre reportes.
 */
public interface ReporteRepository extends JpaRepository<Reporte, Long> {

    // Busca reportes por tipo.
    List<Reporte> findByTipo(TipoReporte tipo);
}