package com.example.fullrepar.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "repuestos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Repuesto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String estado;

    @Column(length = 500)
    private String descripcion;

    private String imagenUrl;

    @Builder.Default
    private Boolean disponible = true;
}