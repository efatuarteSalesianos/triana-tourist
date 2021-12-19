package com.salesianostriana.dam.trianatourist.validaciones.simple.validadores;

import com.salesianostriana.dam.trianatourist.repositorios.RouteRepositorio;
import com.salesianostriana.dam.trianatourist.validaciones.simple.anotaciones.UniqueRouteName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueRouteNameValidator implements ConstraintValidator<UniqueRouteName, String> {

    @Autowired
    private RouteRepositorio repositorio;

    @Override
    public void initialize(UniqueRouteName constraintAnnotation) { }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        return StringUtils.hasText(name) && !repositorio.existsByName(name);
    }
}