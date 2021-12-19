package com.salesianostriana.dam.trianatourist.validaciones.simple.validadores;

import com.salesianostriana.dam.trianatourist.validaciones.simple.anotaciones.IsCoordinate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IsCoordinateValidator implements ConstraintValidator<IsCoordinate, String> {

    @Override
    public void initialize(IsCoordinate constraintAnnotation) { }

    @Override
    public boolean isValid(String location, ConstraintValidatorContext context) {

//        String LATITUDE_PATTERN="^(\\+|-)?(?:90(?:(?:\\.0{1,6})?)|(?:[0-9]|[1-8][0-9])(?:(?:\\.[0-9]{1,6})?))$";
//        String LONGITUDE_PATTERN="^(\\+|-)?(?:180(?:(?:\\.0{1,6})?)|(?:[0-9]|[1-9][0-9]|1[0-7][0-9])(?:(?:\\.[0-9]{1,6})?))$";
//
//        Pattern patronLatitud = Pattern.compile(LATITUDE_PATTERN);
//        Pattern patronLongitud = Pattern.compile(LONGITUDE_PATTERN);
//
//        String latitude = location.substring(0, location.indexOf(","));
//        String longitude = location.substring(0, location.indexOf(",") + 1);
//
//        Matcher matcherLatitud = patronLatitud.matcher(latitude);
//        Matcher matcherLongitud = patronLongitud.matcher(longitude);
//
//        return matcherLatitud.find() && matcherLongitud.find();

        String s = "^([-+]?\\d{1,2}[.]\\d+),\\s*([-+]?\\d{1,3}[.]\\d+)$";

        Pattern p = Pattern.compile(s);

        Matcher m = p.matcher(location);

        return m.find();
    }
}
