package com.salesianostriana.dam.trianatourist.dtos;

import com.salesianostriana.dam.trianatourist.modelos.Category;
import com.salesianostriana.dam.trianatourist.modelos.Route;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RouteDTOConverter {

    private final POIDTOConverter poidtoConverter;

    public GetRouteDTO routeToGetRouteDTO(Route r) {
        return GetRouteDTO.builder()
                .id(r.getId())
                .name(r.getName())
                .steps(r.getSteps().stream().map(poidtoConverter::poiToGetPOIListDTO).collect(Collectors.toList()))
                .build();
    }

    public GetRouteListDTO routeToGetRouteListDTO(Route r) {
        return GetRouteListDTO.builder()
                .id(r.getId())
                .name(r.getName())
                .steps(r.getSteps().size())
                .build();
    }

    public Category saveRouteDtoToRoute(SaveRouteDTO newRoute) {
        return Category.builder()
                .name(newRoute.getName())
                .build();
    }

}