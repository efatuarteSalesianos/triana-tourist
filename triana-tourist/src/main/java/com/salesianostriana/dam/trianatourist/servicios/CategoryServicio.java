package com.salesianostriana.dam.trianatourist.servicios;

import com.salesianostriana.dam.trianatourist.dtos.CategoryDTOConverter;
import com.salesianostriana.dam.trianatourist.dtos.GetCategoryDTO;
import com.salesianostriana.dam.trianatourist.dtos.GetCategoryListDTO;
import com.salesianostriana.dam.trianatourist.dtos.SaveCategoryDTO;
import com.salesianostriana.dam.trianatourist.errores.excepciones.ListEntityNotFoundException;
import com.salesianostriana.dam.trianatourist.errores.excepciones.SingleEntityNotFoundException;
import com.salesianostriana.dam.trianatourist.modelos.Category;
import com.salesianostriana.dam.trianatourist.repositorios.CategoryRepositorio;
import com.salesianostriana.dam.trianatourist.repositorios.POIRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServicio {

    private final CategoryRepositorio repositorio;
    private final CategoryDTOConverter dtoConverter;
    private final POIRepositorio poiRepositorio;

    public Category save(SaveCategoryDTO newCategory) {
        return repositorio.save(Category.builder()
                        .name(newCategory.getName())
                        .build());
    }

    public List<GetCategoryListDTO> findAll() {
        List<Category> result = repositorio.findAll();

        if (result.isEmpty()) {
            throw new ListEntityNotFoundException(Category.class);
        } else {
            return result.stream().map(dtoConverter::categoryToGetCategoryListDTO)
                    .collect(Collectors.toList());
        }
    }

    public GetCategoryDTO findById(UUID id) {
        Optional<Category> result = repositorio.findById(id);

        if (result.isEmpty()) {
            throw new SingleEntityNotFoundException(id.toString(), Category.class);
        } else {
            return result.map(dtoConverter::categoryToGetCategoryDTO).get();
        }
    }

    public GetCategoryDTO edit(UUID id, SaveCategoryDTO category) {

        Optional<Category> encontrada = repositorio.findById(id);

        if (encontrada.isEmpty()) {
            throw new SingleEntityNotFoundException(id.toString(), Category.class);
        } else {
            return encontrada.map(c -> {
                c.setName(category.getName());
                repositorio.save(c);
                return c;
            })
            .map(dtoConverter::categoryToGetCategoryDTO).get();
        }
    }

    public void deleteById(UUID id) {
        Optional<Category> encontrada = repositorio.findById(id);

        if (encontrada.isEmpty()) {
            throw new SingleEntityNotFoundException(id.toString(), Category.class);
        } else if (repositorio.findAll().isEmpty()) {
            throw new ListEntityNotFoundException(Category.class);
        } else {
            encontrada.get().getSitios().forEach(s -> s.removeCategory(encontrada.get()));
            encontrada.get().getSitios().forEach(poiRepositorio::save);
            repositorio.delete(encontrada.get());
        }
    }

}
