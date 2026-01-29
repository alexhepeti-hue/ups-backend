package com.envios.backend.paquete;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaqueteRepository extends JpaRepository<Paquete, Long> {
    Optional<Paquete> findByNumeroGuia(String numeroGuia);
}  