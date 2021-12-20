package com.salesianostriana.dam.trianatourist.repositorios;

import com.salesianostriana.dam.trianatourist.modelos.POI;
import com.salesianostriana.dam.trianatourist.modelos.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface RouteRepositorio extends JpaRepository<Route, UUID> {

    boolean existsByName(String name);

}
