package com.salesianostriana.dam.trianatourist.validaciones.simple.anotaciones;

import com.salesianostriana.dam.trianatourist.validaciones.simple.validadores.IsCoordinateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsCoordinateValidator.class)
public @interface IsCoordinate {

    String message() default "El formato de la ubicación no es correcto. Debe seguir el patrón LL.LLLLLL,LL.LLLLLL";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
