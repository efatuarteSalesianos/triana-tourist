package com.salesianostriana.dam.trianatourist.controladores;

import com.salesianostriana.dam.trianatourist.dtos.GetCategoryDTO;
import com.salesianostriana.dam.trianatourist.dtos.GetCategoryListDTO;
import com.salesianostriana.dam.trianatourist.dtos.SaveCategoryDTO;
import com.salesianostriana.dam.trianatourist.modelos.Category;
import com.salesianostriana.dam.trianatourist.servicios.CategoryServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoriaControlador {

    private final CategoryServicio servicio;

    @PostMapping("/")
    public ResponseEntity<Category> nuevaCategoria(@Valid @RequestBody SaveCategoryDTO newCategory) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.save(newCategory));
    }

    @GetMapping("/")
    public List<GetCategoryListDTO> mostrarTodas() {
        return servicio.findAll();
    }

    @GetMapping("/{id}")
    public GetCategoryDTO buscarUna(@PathVariable UUID id) {
        return servicio.findById(id);
    }

//    @PutMapping("/{id}")
//    public GetCategoryDTO editar(@PathVariable UUID id, @Valid @RequestBody SaveCategoryDTO category) {
//        return servicio.edit(id, category);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarCategoria(@PathVariable UUID id) {
        servicio.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
