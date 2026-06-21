package com.nexocommerce.servicio_reportes.service;

import com.nexocommerce.servicio_reportes.dto.ReporteRequest;
import com.nexocommerce.servicio_reportes.dto.ReporteResponse;
import com.nexocommerce.servicio_reportes.entity.Reporte;
import com.nexocommerce.servicio_reportes.entity.TipoReporte;
import com.nexocommerce.servicio_reportes.exception.ResourceNotFoundException;
import com.nexocommerce.servicio_reportes.repository.ReporteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/*
 * Pruebas unitarias del servicio de reportes.
 * Se prueban los métodos de listar, buscar, crear,
 * listar por tipo y eliminar.
 */
@ExtendWith(MockitoExtension.class)
class ReporteServiceTest {

    @Mock
    private ReporteRepository reporteRepository;

    @InjectMocks
    private ReporteService reporteService;

    private Reporte reporte;
    private ReporteRequest request;

    @BeforeEach
    void setUp() {
        reporte = Reporte.builder()
                .id(1L)
                .nombre("Reporte general de ventas")
                .tipo(TipoReporte.VENTAS)
                .cantidadPedidos(10)
                .cantidadProductos(25)
                .totalVentas(new BigDecimal("350000"))
                .fechaGeneracion(LocalDateTime.now())
                .build();

        request = new ReporteRequest();
        request.setNombre("Reporte general de ventas");
        request.setTipo(TipoReporte.VENTAS);
        request.setCantidadPedidos(10);
        request.setCantidadProductos(25);
        request.setTotalVentas(new BigDecimal("350000"));
    }

    @Test
    void listarReportesCorrectamente() {
        when(reporteRepository.findAll()).thenReturn(List.of(reporte));

        List<ReporteResponse> resultado = reporteService.listar();

        assertEquals(1, resultado.size());
        assertEquals("Reporte general de ventas", resultado.get(0).getNombre());
    }

    @Test
    void buscarReportePorIdCorrectamente() {
        when(reporteRepository.findById(1L)).thenReturn(Optional.of(reporte));

        ReporteResponse response = reporteService.buscarPorId(1L);

        assertNotNull(response);
        assertEquals(1L, response.getId());
    }

    @Test
    void buscarReporteNoExistenteLanzaExcepcion() {
        when(reporteRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> reporteService.buscarPorId(99L));
    }

    @Test
    void listarReportesPorTipoCorrectamente() {
        when(reporteRepository.findByTipo(TipoReporte.VENTAS)).thenReturn(List.of(reporte));

        List<ReporteResponse> resultado = reporteService.listarPorTipo(TipoReporte.VENTAS);

        assertEquals(1, resultado.size());
        assertEquals(TipoReporte.VENTAS, resultado.get(0).getTipo());
    }

    @Test
    void crearReporteCorrectamente() {
        when(reporteRepository.save(any(Reporte.class))).thenReturn(reporte);

        ReporteResponse response = reporteService.crear(request);

        assertNotNull(response);
        assertEquals("Reporte general de ventas", response.getNombre());

        verify(reporteRepository).save(any(Reporte.class));
    }

    @Test
    void eliminarReporteCorrectamente() {
        when(reporteRepository.findById(1L)).thenReturn(Optional.of(reporte));

        reporteService.eliminar(1L);

        verify(reporteRepository).delete(reporte);
    }
}