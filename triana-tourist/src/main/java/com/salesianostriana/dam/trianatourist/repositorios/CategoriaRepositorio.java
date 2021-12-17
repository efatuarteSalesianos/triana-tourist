package com.salesianostriana.dam.trianatourist.repositorios;

import com.salesianostriana.dam.trianatourist.modelos.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoriaRepositorio extends JpaRepository<Categoria, UUID> {
}
