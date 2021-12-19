package com.salesianostriana.dam.trianatourist.controladores;

import com.salesianostriana.dam.trianatourist.dtos.*;
import com.salesianostriana.dam.trianatourist.modelos.Category;
import com.salesianostriana.dam.trianatourist.modelos.Route;
import com.salesianostriana.dam.trianatourist.servicios.CategoryServicio;
import com.salesianostriana.dam.trianatourist.servicios.RouteServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/route")
public class RouteControlador {

    private final RouteServicio servicio;

    @PostMapping("/")
    public ResponseEntity<Route> nuevaRuta(@Valid @RequestBody SaveRouteDTO newRoute) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.save(newRoute));
    }

    @GetMapping("/")
    public List<GetRouteListDTO> mostrarTodas() {
        return servicio.findAll();
    }

    @GetMapping("/{id}")
    public GetRouteDTO buscarUna(@PathVariable UUID id) {
        return servicio.findById(id);
    }

    @PutMapping("/{id}")
    public GetRouteDTO editar(@PathVariable UUID id, @Valid @RequestBody SaveRouteDTO route) {
        return servicio.edit(id, route);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarRuta(@PathVariable UUID id) {
        servicio.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/poi/{id2}")
    public ResponseEntity<GetRouteDTO> addPOI(@PathVariable UUID id, @PathVariable UUID id2) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.addPOIToRoute(id, id2));
    }

    @DeleteMapping("/{id}/poi/{id2}")
    public ResponseEntity<?> eliminarPOI(@PathVariable UUID id, @PathVariable UUID id2) {
        servicio.removePOIFromRoute(id, id2);
        return ResponseEntity
                .noContent()
                .build();
    }

}