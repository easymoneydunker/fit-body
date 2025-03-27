package ru.easymoneydunker.exception.model.notfound;

public class MealNotFoundException extends RuntimeException {
    public MealNotFoundException(String message) {
        super(message);
    }
}
