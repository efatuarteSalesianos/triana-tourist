package com.salesianostriana.dam.trianatourist.errores.excepciones;

public class ObjectInListException extends RuntimeException{
    public ObjectInListException(String id, Class clazz, Class clazz2) {
        super(String.format("Ya existe un objeto de tipo %s con ID %s en lista de %s.", clazz.getName(), id, clazz2.getName()));
    }
}
