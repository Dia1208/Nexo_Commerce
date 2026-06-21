package com.nexocommerce.servicio_reportes.service;

import com.nexocommerce.servicio_reportes.dto.ReporteRequest;
import com.nexocommerce.servicio_reportes.dto.ReporteResponse;
import com.nexocommerce.servicio_reportes.entity.Reporte;
import com.nexocommerce.servicio_reportes.entity.TipoReporte;
import com.nexocommerce.servicio_reportes.exception.ResourceNotFoundException;
import com.nexocommerce.servicio_reportes.repository.ReporteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/*
 * Esta clase contiene la lógica de negocio de reportes.
 * Aquí se crean, consultan y eliminan reportes.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ReporteService {

    private final ReporteRepository reporteRepository;

    // Lista todos los reportes.
    public List<ReporteResponse> listar() {
        return reporteRepository.findAll()
                .stream()
                .map(this::mapearAResponse)
                .toList();
    }

    // Busca un reporte por id.
    public ReporteResponse buscarPorId(Long id) {
        Reporte reporte = reporteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reporte no encontrado con id: " + id));

        return mapearAResponse(reporte);
    }

    // Lista reportes por tipo.
    public List<ReporteResponse> listarPorTipo(TipoReporte tipo) {
        return reporteRepository.findByTipo(tipo)
                .stream()
                .map(this::mapearAResponse)
                .toList();
    }

    // Crea un nuevo reporte.
    public ReporteResponse crear(ReporteRequest request) {
        Reporte reporte = Reporte.builder()
                .nombre(request.getNombre())
                .tipo(request.getTipo())
                .cantidadPedidos(request.getCantidadPedidos())
                .cantidadProductos(request.getCantidadProductos())
                .totalVentas(request.getTotalVentas())
                .fechaGeneracion(LocalDateTime.now())
                .build();

        Reporte reporteGuardado = reporteRepository.save(reporte);

        log.info("Reporte creado con id: {}", reporteGuardado.getId());

        return mapearAResponse(reporteGuardado);
    }

    // Elimina un reporte.
    public void eliminar(Long id) {
        Reporte reporte = reporteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reporte no encontrado con id: " + id));

        reporteRepository.delete(reporte);

        log.info("Reporte eliminado con id: {}", id);
    }

    // Convierte entidad a DTO.
    private ReporteResponse mapearAResponse(Reporte reporte) {
        return new ReporteResponse(
                reporte.getId(),
                reporte.getNombre(),
                reporte.getTipo(),
                reporte.getCantidadPedidos(),
                reporte.getCantidadProductos(),
                reporte.getTotalVentas(),
                reporte.getFechaGeneracion()
        );
    }
}