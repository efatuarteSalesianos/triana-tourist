package com.salesianostriana.dam.trianatourist.controladores;

import com.salesianostriana.dam.trianatourist.dtos.*;
import com.salesianostriana.dam.trianatourist.modelos.POI;
import com.salesianostriana.dam.trianatourist.servicios.POIServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/poi")
public class POIControlador {

    private final POIServicio servicio;

    @PostMapping("/")
    public ResponseEntity<GetPOIDTO> nuevoSitio(@Valid @RequestBody SavePOIDTO newPOI) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.save(newPOI));
    }

    @GetMapping("/")
    public List<GetPOIListDTO> mostrarTodas() {
        return servicio.findAll();
    }

    @GetMapping("/{id}")
    public GetPOIDTO buscarUna(@PathVariable UUID id) {
        return servicio.findById(id);
    }

    @PutMapping("/{id}")
    public GetPOIDTO editar(@PathVariable UUID id, @Valid @RequestBody SavePOIDTO poi) {
        return servicio.edit(id, poi);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarSitio(@PathVariable UUID id) {
        servicio.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/category/{id2}")
    public ResponseEntity<GetPOIDTO> asignarCategoria(@PathVariable UUID id, @PathVariable UUID id2) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.addCategoria(id, id2));
    }

    @DeleteMapping("/{id}/category/{id2}")
    public ResponseEntity<?> quitarCategoria(@PathVariable UUID id, @PathVariable UUID id2) {
        servicio.removeCategoria(id, id2);
        return ResponseEntity
                .noContent()
                .build();
    }
}
