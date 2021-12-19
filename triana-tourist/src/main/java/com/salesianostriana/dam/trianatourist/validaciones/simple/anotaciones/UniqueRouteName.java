package com.salesianostriana.dam.trianatourist.validaciones.simple.anotaciones;

import com.salesianostriana.dam.trianatourist.validaciones.simple.validadores.UniqueNameValidator;
import com.salesianostriana.dam.trianatourist.validaciones.simple.validadores.UniqueRouteNameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueRouteNameValidator.class)
public @interface UniqueRouteName {

    String message() default "El nombre de la ruta ya existe. Pruebe a introducir uno distinto";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
