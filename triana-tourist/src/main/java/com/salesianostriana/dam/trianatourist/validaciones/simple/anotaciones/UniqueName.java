package com.salesianostriana.dam.trianatourist.validaciones.simple.anotaciones;

import com.salesianostriana.dam.trianatourist.validaciones.simple.validadores.UniqueNameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueNameValidator.class)
public @interface UniqueName {

    String message() default "El nombre del producto debe ser único";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}