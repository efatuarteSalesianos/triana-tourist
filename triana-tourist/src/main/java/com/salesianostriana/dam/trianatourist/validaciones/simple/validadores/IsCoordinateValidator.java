package com.salesianostriana.dam.trianatourist.validaciones.simple.validadores;

import com.salesianostriana.dam.trianatourist.validaciones.simple.anotaciones.IsCoordinate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsCoordinateValidator implements ConstraintValidator<IsCoordinate, String> {

    private String LATITUDE_PATTERN="^(\\+|-)?(?:90(?:(?:\\.0{1,6})?)|(?:[0-9]|[1-8][0-9])(?:(?:\\.[0-9]{1,6})?))$";
    private String LONGITUDE_PATTERN="^(\\+|-)?(?:180(?:(?:\\.0{1,6})?)|(?:[0-9]|[1-9][0-9]|1[0-7][0-9])(?:(?:\\.[0-9]{1,6})?))$";

    @Override
    public void initialize(IsCoordinate constraintAnnotation) { }

    @Override
    public boolean isValid(String location, ConstraintValidatorContext context) {

        String latitude = location.substring(0, location.indexOf(","));
        String longitude = location.substring(0, location.indexOf(",") + 1);

        if(latitude.matches(LATITUDE_PATTERN) && longitude.matches(LONGITUDE_PATTERN)){
            return true;
        } else {
            return false;
        }
    }
}
