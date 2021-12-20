package com.salesianostriana.dam.trianatourist.validaciones.multiple.validadores;

import com.salesianostriana.dam.trianatourist.validaciones.multiple.anotaciones.UniqueUrl;
import org.springframework.beans.PropertyAccessorFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUrlValidator implements ConstraintValidator<UniqueUrl, Object> {

    private String url1;
    private String url2;

    @Override
    public void initialize(UniqueUrl constraintAnnotation) {
        this.url1 = constraintAnnotation.url1();
        this.url2 = constraintAnnotation.url2();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        Object url1Value = PropertyAccessorFactory.forBeanPropertyAccess(value).getPropertyValue(url1);
        Object url2Value = PropertyAccessorFactory.forBeanPropertyAccess(value).getPropertyValue(url2);

        if (url1Value != "" || url2Value != "") {
            return !url1Value.equals(url2Value);
        } else {
            return true;
        }
    }

}
