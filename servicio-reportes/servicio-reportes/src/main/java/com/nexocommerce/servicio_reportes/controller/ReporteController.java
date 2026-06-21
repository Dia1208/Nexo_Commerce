package com.nexocommerce.servicio_reportes.controller;

import com.nexocommerce.servicio_reportes.dto.ReporteRequest;
import com.nexocommerce.servicio_reportes.dto.ReporteResponse;
import com.nexocommerce.servicio_reportes.entity.TipoReporte;
import com.nexocommerce.servicio_reportes.service.ReporteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
 * Controlador REST del microservicio de reportes.
 * Expone endpoints para gestionar reportes.
 */
@RestController
@RequestMapping("/api/reportes")
@RequiredArgsConstructor
public class ReporteController {

    private final ReporteService reporteService;

    // Lista todos los reportes.
    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(reporteService.listar());
    }

    // Busca un reporte por id.
    @GetMapping("/{id}")
    public ResponseEntity<ReporteResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(reporteService.buscarPorId(id));
    }

    // Lista reportes por tipo.
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<?> listarPorTipo(@PathVariable TipoReporte tipo) {
        return ResponseEntity.ok(reporteService.listarPorTipo(tipo));
    }

    // Crea un nuevo reporte.
    @PostMapping
    public ResponseEntity<ReporteResponse> crear(@Valid @RequestBody ReporteRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(reporteService.crear(request));
    }

    // Elimina un reporte.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        reporteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}