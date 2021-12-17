package com.salesianostriana.dam.trianatourist.repositorios;

import com.salesianostriana.dam.trianatourist.modelos.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RouteRepository extends JpaRepository<Route, UUID> {
}
