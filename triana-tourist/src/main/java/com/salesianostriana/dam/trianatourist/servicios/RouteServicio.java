package com.salesianostriana.dam.trianatourist.servicios;

import com.salesianostriana.dam.trianatourist.dtos.*;
import com.salesianostriana.dam.trianatourist.errores.excepciones.ListEntityNotFoundException;
import com.salesianostriana.dam.trianatourist.errores.excepciones.SingleEntityNotFoundException;
import com.salesianostriana.dam.trianatourist.modelos.Category;
import com.salesianostriana.dam.trianatourist.modelos.POI;
import com.salesianostriana.dam.trianatourist.modelos.Route;
import com.salesianostriana.dam.trianatourist.repositorios.POIRepositorio;
import com.salesianostriana.dam.trianatourist.repositorios.RouteRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RouteServicio {

    private final RouteRepositorio repositorio;
    private final RouteDTOConverter dtoConverter;
    private final POIRepositorio poiRepositorio;

    public Route save(SaveRouteDTO newRoute) {
        return repositorio.save(Route.builder()
                .name(newRoute.getName())
                .build());
    }

    public List<GetRouteListDTO> findAll() {
        List<Route> result = repositorio.findAll();

        if (result.isEmpty()) {
            throw new ListEntityNotFoundException(Route.class);
        } else {
            return result.stream().map(dtoConverter::routeToGetRouteListDTO)
                    .collect(Collectors.toList());
        }
    }

    public GetRouteDTO findById(UUID id) {
        Optional<Route> result = repositorio.findById(id);

        if (result.isEmpty()) {
            throw new SingleEntityNotFoundException(id.toString(), Route.class);
        } else {
            return result.map(dtoConverter::routeToGetRouteDTO).get();
        }
    }

    public GetRouteDTO edit(UUID id, SaveRouteDTO route) {

        Optional<Route> encontrada = repositorio.findById(id);

        if (encontrada.isEmpty()) {
            throw new SingleEntityNotFoundException(id.toString(), Route.class);
        } else {
            return encontrada.map(c -> {
                c.setName(route.getName());
                repositorio.save(c);
                return c;
            })
            .map(dtoConverter::routeToGetRouteDTO).get();
        }
    }

    public void deleteById(UUID id) {
        Optional<Route> encontrada = repositorio.findById(id);

        if (encontrada.isEmpty()) {
            throw new SingleEntityNotFoundException(id.toString(), Route.class);
        } else if (repositorio.findAll().isEmpty()) {
            throw new ListEntityNotFoundException(Route.class);
        } else {
            encontrada.get().getSteps().forEach(s -> s.removeRoute(encontrada.get()));
            repositorio.deleteById(id);
        }
    }

    public GetRouteDTO addPOIToRoute(UUID routeId, UUID poiID) {
        Optional<Route> route = repositorio.findById(routeId);
        Optional<POI> sitio = poiRepositorio.findById(poiID);

        if (route.isEmpty()) {
            throw new SingleEntityNotFoundException(routeId.toString(), Route.class);
        } else if (sitio.isEmpty()) {
            throw new SingleEntityNotFoundException(poiID.toString(), POI.class);
        } else {
            sitio.get().addRoute(route.get());
            repositorio.save(route.get());
            return route.map(dtoConverter::routeToGetRouteDTO).get();
        }
    }

    public void removePOIFromRoute(UUID routeId, UUID poiId) {
        Optional<Route> route = repositorio.findById(routeId);
        Optional<POI> sitio = poiRepositorio.findById(poiId);

        if (route.isEmpty()) {
            throw new SingleEntityNotFoundException(routeId.toString(), Route.class);
        } else if (sitio.isEmpty()) {
            throw new SingleEntityNotFoundException(poiId.toString(), POI.class);
        } else {
            sitio.get().removeRoute(route.get());
            repositorio.save(route.get());
        }
    }

}