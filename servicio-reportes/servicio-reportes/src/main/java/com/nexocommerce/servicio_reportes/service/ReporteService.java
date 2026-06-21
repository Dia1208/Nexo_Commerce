/*
 * Decompiled with CFR 0.151.
 *
 * Could not load the following classes:
 *  lombok.Generated
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.stereotype.Service
 */
package com.nexocommerce.servicio_reportes.service;

import com.nexocommerce.servicio_reportes.dto.ReporteRequest;
import com.nexocommerce.servicio_reportes.dto.ReporteResponse;
import com.nexocommerce.servicio_reportes.entity.Reporte;
import com.nexocommerce.servicio_reportes.exception.ResourceNotFoundException;
import com.nexocommerce.servicio_reportes.repository.ReporteRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ReporteService {
    @Generated
    private static final Logger log = LoggerFactory.getLogger(ReporteService.class);
    private final ReporteRepository reporteRepository;

    public List<ReporteResponse> listar() {
        return this.reporteRepository.findAll().stream().map(this::mapearAResponse).toList();
    }

    public ReporteResponse buscarPorId(Long id) {
        Reporte reporte = (Reporte)this.reporteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        return this.mapearAResponse(reporte);
    }

    public ReporteResponse crear(ReporteRequest request) {
        Reporte reporte = Reporte.builder().nombre(request.getNombre()).tipo(request.getTipo()).cantidadPedidos(request.getCantidadPedidos()).cantidadProductos(request.getCantidadProductos()).totalVentas(request.getTotalVentas()).fechaGeneracion(LocalDateTime.now()).build();
        Reporte reporteGuardado = (Reporte)this.reporteRepository.save(reporte);
        log.info("Reporte creado con id: {}", (Object)reporteGuardado.getId());
        return this.mapearAResponse(reporteGuardado);
    }

    public void eliminar(Long id) {
        Reporte reporte = (Reporte)this.reporteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        this.reporteRepository.delete(reporte);
        log.info("Reporte eliminado con id: {}", (Object)id);
    }

    private ReporteResponse mapearAResponse(Reporte reporte) {
        return new ReporteResponse(reporte.getId(), reporte.getNombre(), reporte.getTipo(), reporte.getCantidadPedidos(), reporte.getCantidadProductos(), reporte.getTotalVentas(), reporte.getFechaGeneracion());
    }

    @Generated
    public ReporteService(ReporteRepository reporteRepository) {
        this.reporteRepository = reporteRepository;
    }
}