package org.example.test_stajirovka.exceptions;

public class DishesNotFoundException extends RuntimeException{
    public DishesNotFoundException(String string) {
        super(string);
    }

    public DishesNotFoundException() {
        super();
    }
}
