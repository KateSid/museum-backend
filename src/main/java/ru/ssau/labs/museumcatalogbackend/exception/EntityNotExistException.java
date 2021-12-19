package ru.ssau.labs.museumcatalogbackend.exception;

public class EntityNotExistException extends IllegalStateException {
    public EntityNotExistException(String message) {
        super(message);
    }
}
