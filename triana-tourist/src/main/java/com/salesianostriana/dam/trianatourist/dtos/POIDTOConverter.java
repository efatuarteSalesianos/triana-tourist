package com.salesianostriana.dam.trianatourist.dtos;

import com.salesianostriana.dam.trianatourist.modelos.POI;
import org.springframework.stereotype.Component;

@Component
public class POIDTOConverter {

    public GetPOIDTO poiToGetPOIDTO(POI poi) {
        return GetPOIDTO.builder()
                .id(poi.getId())
                .name(poi.getName())
                .location(poi.getLocation())
                .description(poi.getDescription())
                .coverPhoto(poi.getCoverPhoto())
                .photo2(poi.getPhoto2())
                .photo3(poi.getPhoto3())
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
}
