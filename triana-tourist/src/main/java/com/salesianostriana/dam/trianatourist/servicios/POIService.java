package com.salesianostriana.dam.trianatourist.servicios;

import com.salesianostriana.dam.trianatourist.dtos.*;
import com.salesianostriana.dam.trianatourist.errores.excepciones.ListEntityNotFoundException;
import com.salesianostriana.dam.trianatourist.errores.excepciones.SingleEntityNotFoundException;
import com.salesianostriana.dam.trianatourist.modelos.Category;
import com.salesianostriana.dam.trianatourist.modelos.POI;
import com.salesianostriana.dam.trianatourist.repositorios.CategoryRepositorio;
import com.salesianostriana.dam.trianatourist.repositorios.POIRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class POIService {

    private final CategoryRepositorio categoryRepositorio;
    private final POIRepository repositorio;
    private final POIDTOConverter dtoConverter;

    public POI save(SavePOIDTO newPOI) {

        Optional<Category> categoria = categoryRepositorio.findById(newPOI.getCategoryId());

        if (categoria.isEmpty()) {
            throw new SingleEntityNotFoundException(newPOI.getCategoryId().toString(), Category.class);
        } else {
            return repositorio.save(POI.builder()
                            .name(newPOI.getName())
                            .location(newPOI.getLocation())
                            .description(newPOI.getDescription())
                            .coverPhoto(newPOI.getCoverPhoto())
                            .photo2(newPOI.getPhoto2())
                            .photo3(newPOI.getPhoto3())
                            .category(categoryRepositorio.findById(newPOI.getCategoryId()).get())
                            .build());
        }
    }

    public List<GetPOIListDTO> findAll() {
        List<POI> result = repositorio.findAll();

        if (result.isEmpty()) {
            throw new ListEntityNotFoundException(Category.class);
        } else {
            return result.stream().map(dtoConverter::poiToGetPOIListDTO)
                    .collect(Collectors.toList());
        }
    }

    public GetPOIDTO findById(UUID id) {
        Optional<POI> result = repositorio.findById(id);

        if (result.isEmpty()) {
            throw new SingleEntityNotFoundException(id.toString(), Category.class);
        } else {
            return result.map(dtoConverter::poiToGetPOIDTO).get();
        }
    }

//    public GetCategoryDTO edit(UUID id, SaveCategoryDTO category) {
//
//        Optional<Category> encontrada = repositorio.findById(id);
//
//        if (encontrada.isEmpty()) {
//            throw new SingleEntityNotFoundException(id.toString(), Category.class);
//        } else {
//            return repositorio.findById(id).map(c -> {
//                c.getName(category.getName());
//                repositorio.save(c);
//                return c;
//            })
//            .map(dtoConverter::categoryToGetCategoryDTO);
//        }
//    }

    public void deleteById(UUID id) {
        Optional<POI> sitio = repositorio.findById(id);

        if (sitio.isEmpty()) {
            throw new SingleEntityNotFoundException(id.toString(), Category.class);
        } else if (repositorio.findAll().isEmpty()) {
            throw new ListEntityNotFoundException(Category.class);
        } else {
            repositorio.deleteById(id);
        }
    }

}
