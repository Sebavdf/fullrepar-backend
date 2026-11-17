package com.example.fullrepar.controller;

import com.example.fullrepar.model.Repuesto;
import com.example.fullrepar.repository.RepuestoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/repuestos")
@RequiredArgsConstructor
public class RepuestoController {

    private final RepuestoRepository repuestoRepository;

    // Público: cualquiera que visite FullRepar ve los repuestos disponibles
    @GetMapping
    public ResponseEntity<List<Repuesto>> listarDisponibles() {
        return ResponseEntity.ok(repuestoRepository.findByDisponibleTrue());
    }

    // Protegido por JWT: solo tú con token puedes agregar repuestos
    @PostMapping
    public ResponseEntity<Repuesto> crear(@RequestBody Repuesto repuesto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repuestoRepository.save(repuesto));
    }

    // Protegido por JWT: editar stock o datos
    @PutMapping("/{id}")
    public ResponseEntity<Repuesto> actualizar(@PathVariable Long id, @RequestBody Repuesto datos) {
        return repuestoRepository.findById(id)
                .map(rep -> {
                    rep.setNombre(datos.getNombre());
                    rep.setEstado(datos.getEstado());
                    rep.setDescripcion(datos.getDescripcion());
                    rep.setImagenUrl(datos.getImagenUrl());
                    rep.setDisponible(datos.getDisponible());
                    return ResponseEntity.ok(repuestoRepository.save(rep));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Protegido por JWT: eliminar repuesto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!repuestoRepository.existsById(id)) return ResponseEntity.notFound().build();
        repuestoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}