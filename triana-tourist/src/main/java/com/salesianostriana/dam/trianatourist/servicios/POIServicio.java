package com.salesianostriana.dam.trianatourist.servicios;

import com.salesianostriana.dam.trianatourist.dtos.*;
import com.salesianostriana.dam.trianatourist.errores.excepciones.ListEntityNotFoundException;
import com.salesianostriana.dam.trianatourist.errores.excepciones.SingleEntityNotFoundException;
import com.salesianostriana.dam.trianatourist.modelos.Category;
import com.salesianostriana.dam.trianatourist.modelos.POI;
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
public class POIServicio {

    private final CategoryRepositorio categoryRepositorio;
    private final POIRepositorio repositorio;
    private final POIDTOConverter dtoConverter;

    public GetPOIDTO save(SavePOIDTO newPOI) {

        Optional<Category> categoria = categoryRepositorio.findById(newPOI.getCategoryId());
        if (categoria.isEmpty()){
            throw new SingleEntityNotFoundException(newPOI.getCategoryId().toString(), Category.class);
        } else {
            POI sitio = dtoConverter.savePOIDtoToPOI(newPOI);
            repositorio.save(sitio);
            return dtoConverter.savePOIDtoToGetPOIDto(newPOI, sitio);
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

    public GetPOIDTO edit(UUID id, SavePOIDTO poi) {

        Optional<POI> encontrada = repositorio.findById(id);

        if (encontrada.isEmpty()) {
            throw new SingleEntityNotFoundException(id.toString(), POI.class);
        } else {
            return encontrada.map(p -> {
                p.setName(poi.getName());
                p.setLocation(poi.getLocation());
                p.setDescription(poi.getDescription());
                p.setCoverPhoto(poi.getCoverPhoto());
                p.setPhoto2(poi.getPhoto2());
                p.setPhoto3(poi.getPhoto3());
                repositorio.save(p);
                return p;
            })
            .map(dtoConverter::poiToGetPOIDTO).get();
        }
    }

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

    public GetPOIDTO addCategoria(UUID poiID, UUID categoryId) {
        Optional<Category> categoria = categoryRepositorio.findById(categoryId);
        Optional<POI> sitio = repositorio.findById(poiID);

        if (categoria.isEmpty()) {
            throw new SingleEntityNotFoundException(categoryId.toString(), Category.class);
        } else if (sitio.isEmpty()) {
            throw new SingleEntityNotFoundException(poiID.toString(), POI.class);
        } else {
            sitio.get().addCategory(categoria.get());
            repositorio.save(sitio.get());
            return sitio.map(dtoConverter::poiToGetPOIDTO).get();
        }
    }

    public void removeCategoria(UUID poiID, UUID categoryId) {
        Optional<Category> categoria = categoryRepositorio.findById(categoryId);
        Optional<POI> sitio = repositorio.findById(poiID);

        if (categoria.isEmpty()) {
            throw new SingleEntityNotFoundException(categoryId.toString(), Category.class);
        } else if (sitio.isEmpty()) {
            throw new SingleEntityNotFoundException(poiID.toString(), POI.class);
        } else {
            sitio.get().removeCategory(categoria.get());
            repositorio.save(sitio.get());
        }
    }
}
