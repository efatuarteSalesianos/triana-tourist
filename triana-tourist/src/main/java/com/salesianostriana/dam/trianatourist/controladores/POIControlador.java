package com.salesianostriana.dam.trianatourist.controladores;

import com.salesianostriana.dam.trianatourist.dtos.*;
import com.salesianostriana.dam.trianatourist.modelos.Category;
import com.salesianostriana.dam.trianatourist.modelos.POI;
import com.salesianostriana.dam.trianatourist.servicios.CategoryServicio;
import com.salesianostriana.dam.trianatourist.servicios.POIService;
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

    private final POIService servicio;

    @PostMapping("/")
    public ResponseEntity<POI> nuevoSitio(@Valid @RequestBody SavePOIDTO newPOI) {
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

//    @PutMapping("/{id}")
//    public GetCategoryDTO editar(@PathVariable UUID id, @Valid @RequestBody SaveCategoryDTO category) {
//        return servicio.edit(id, category);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarSitio(@PathVariable UUID id) {
        servicio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
