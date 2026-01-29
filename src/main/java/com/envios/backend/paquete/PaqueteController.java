package com.envios.backend.paquete;

import java.util.UUID;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;



@RestController
@RequestMapping("/api/paquetes")
@CrossOrigin(origins = "*")
public class PaqueteController {

    @Autowired
    private PaqueteRepository repository;

    @PostMapping("/crear")
public Paquete crear(@RequestBody Paquete paquete) {
    paquete.setNumeroGuia(UUID.randomUUID().toString()); 
    paquete.setEstado("CREADO");
    return repository.save(paquete);
}

    

   @GetMapping("/consultar/{guia}")
public ResponseEntity<Object> consultar(@PathVariable String guia) {

    Optional<Paquete> resultado = repository.findByNumeroGuia(guia);

    if (resultado.isPresent()) {
        return ResponseEntity.ok(resultado.get());
    } else {
        return ResponseEntity.status(404).body("Guía no encontrada");
    }
}

  @PutMapping("/estado/{guia}")
public ResponseEntity<Object> cambiarEstado(
        @PathVariable String guia,
        @RequestParam String estado) {

    Optional<Paquete> resultado = repository.findByNumeroGuia(guia);

    if (resultado.isPresent()) {
        Paquete paquete = resultado.get();
        paquete.setEstado(estado);
        return ResponseEntity.ok(repository.save(paquete));
    } else {
        return ResponseEntity.status(404).body("Guía no encontrada");
    }
}
}