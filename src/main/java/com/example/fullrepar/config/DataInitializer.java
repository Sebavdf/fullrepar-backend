package com.example.fullrepar.config;

import com.example.fullrepar.model.Repuesto;
import com.example.fullrepar.model.Usuario;
import com.example.fullrepar.repository.RepuestoRepository;
import com.example.fullrepar.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RepuestoRepository repuestoRepo;
    private final UsuarioRepository usuarioRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (usuarioRepo.count() == 0) {
            usuarioRepo.save(Usuario.builder()
                .username("admin")
                .password(passwordEncoder.encode("FullRepar2026"))
                .rol("ADMIN")
                .build());
        }

        if (repuestoRepo.count() == 0) {
            repuestoRepo.saveAll(List.of(
                Repuesto.builder()
                    .nombre("Bomba de desagüe universal")
                    .estado("Nuevo")
                    .descripcion("Compatible con LG, Samsung y Daewoo.")
                    .disponible(true)
                    .build(),
                Repuesto.builder()
                    .nombre("Placa lógica lavadora (Desarme chequeada)")
                    .estado("Chequeada en banco")
                    .descripcion("Probada al 100% con voltajes estables de control y potencia.")
                    .disponible(true)
                    .build()
            ));
        }
    }
}