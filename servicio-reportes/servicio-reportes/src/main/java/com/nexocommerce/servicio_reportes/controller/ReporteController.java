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

import java.util.List;

/*
 * Controlador REST del microservicio de reportes.
 * Expone los endpoints para listar, crear, buscar y eliminar reportes.
 */
@RestController
@RequestMapping("/api/reportes")
@RequiredArgsConstructor
public class ReporteController {

    private final ReporteService reporteService;

    // Lista todos los reportes registrados.
    @GetMapping
    public ResponseEntity<List<ReporteResponse>> listar() {
        return ResponseEntity.ok(reporteService.listar());
    }

    // Busca un reporte por su identificador.
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(reporteService.buscarPorId(id));
    }

    // Crea un nuevo reporte.
    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody ReporteRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reporteService.crear(request));
    }

    // Elimina un reporte por su identificador.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        reporteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<ReporteResponse>> listarPorTipo(@PathVariable TipoReporte tipo) {
        return ResponseEntity.ok(reporteService.listarPorTipo(tipo));
    }
}