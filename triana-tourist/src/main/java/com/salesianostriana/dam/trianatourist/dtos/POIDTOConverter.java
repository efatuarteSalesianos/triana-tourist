package com.salesianostriana.dam.trianatourist.dtos;

import com.salesianostriana.dam.trianatourist.modelos.Category;
import com.salesianostriana.dam.trianatourist.modelos.POI;
import com.salesianostriana.dam.trianatourist.repositorios.CategoryRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class POIDTOConverter {

    private final CategoryRepositorio categoryRepositorio;

    public GetPOIDTO poiToGetPOIDTO(POI poi) {
        return GetPOIDTO.builder()
                .id(poi.getId())
                .name(poi.getName())
                .location(poi.getLocation())
                .description(poi.getDescription())
                .coverPhoto(poi.getCoverPhoto())
                .photo2(poi.getPhoto2())
                .photo3(poi.getPhoto3())
                .date(poi.getDate())
                .category(poi.getCategory().getName())
                .build();
    }

    public GetPOIListDTO poiToGetPOIListDTO(POI poi) {
        return GetPOIListDTO.builder()
                .id(poi.getId())
                .name(poi.getName())
                .location(poi.getLocation())
                .coverPhoto(poi.getCoverPhoto())
                .category(poi.getCategory().getName())
                .build();
    }

    public POI savePOIDtoToPOI (SavePOIDTO poi) {
        return POI.builder()
                .name(poi.getName())
                .location(poi.getLocation())
                .description(poi.getDescription())
                .coverPhoto(poi.getCoverPhoto())
                .photo2(poi.getPhoto2())
                .photo3(poi.getPhoto3())
                .date(poi.getDate())
                .category(categoryRepositorio.findById(poi.getCategoryId()).get())
                .build();
    }

    public GetPOIDTO savePOIDtoToGetPOIDto (SavePOIDTO newPoi, POI poi) {
        return GetPOIDTO.builder()
                .id(poi.getId())
                .name(newPoi.getName())
                .location(newPoi.getLocation())
                .description(newPoi.getDescription())
                .coverPhoto(newPoi.getCoverPhoto())
                .photo2(newPoi.getPhoto2())
                .photo3(newPoi.getPhoto3())
                .date(newPoi.getDate())
                .category(categoryRepositorio.findById(newPoi.getCategoryId()).get().getName())
                .build();
    }

}
