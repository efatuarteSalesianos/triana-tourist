package com.salesianostriana.dam.trianatourist.repositorios;

import com.salesianostriana.dam.trianatourist.modelos.POI;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface POIRepositorio extends JpaRepository<POI, UUID> {

}
