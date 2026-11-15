package com.example.fullrepar.repository;

import com.example.fullrepar.model.Repuesto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RepuestoRepository extends JpaRepository<Repuesto, Long> {
    List<Repuesto> findByDisponibleTrue();
}