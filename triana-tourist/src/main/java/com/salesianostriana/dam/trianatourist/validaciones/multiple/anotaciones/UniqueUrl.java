package com.salesianostriana.dam.trianatourist.validaciones.multiple.anotaciones;

import com.salesianostriana.dam.trianatourist.validaciones.multiple.validadores.UniqueUrlValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueUrlValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueUrl {

    String message() default "No se pueden subir dos fotos iguales";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String url1();
    String url2();

    @Target({ ElementType.TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        UniqueUrl[] value();
    }

}
