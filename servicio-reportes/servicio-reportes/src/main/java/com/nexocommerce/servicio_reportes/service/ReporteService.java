package com.nexocommerce.servicio_reportes.service;

import com.nexocommerce.servicio_reportes.dto.ReporteRequest;
import com.nexocommerce.servicio_reportes.dto.ReporteResponse;
import com.nexocommerce.servicio_reportes.entity.Reporte;
import com.nexocommerce.servicio_reportes.entity.TipoReporte;
import com.nexocommerce.servicio_reportes.exception.ResourceNotFoundException;
import com.nexocommerce.servicio_reportes.repository.ReporteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/*
 * Servicio encargado de la lógica de negocio relacionada con reportes.
 */
@Service

/*
 * Genera automáticamente el constructor para inyectar
 * el repositorio ReporteRepository en este servicio.
 */
@RequiredArgsConstructor
public class ReporteService {

    private final ReporteRepository reporteRepository;

    // Lista todos los reportes registrados.
    public List<ReporteResponse> listar() {
        return reporteRepository.findAll()
                .stream()
                .map(this::mapearAResponse)
                .toList();
    }

    // Busca un reporte por su ID.
    public ReporteResponse buscarPorId(Long id) {
        Reporte reporte = reporteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        return mapearAResponse(reporte);
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

        return mapearAResponse(reporteGuardado);
    }

    // Elimina un reporte por su ID.
    public void eliminar(Long id) {
        Reporte reporte = reporteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        reporteRepository.delete(reporte);
    }

    // Convierte Reporte a ReporteResponse.
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
    // Lista los reportes filtrados por tipo.
    public List<ReporteResponse> listarPorTipo(TipoReporte tipo) {
        return reporteRepository.findByTipo(tipo)
                .stream()
                .map(this::mapearAResponse)
                .toList();
    }
}