package org.example.test_stajirovka.exceptions;

public class MealNotFoundException extends RuntimeException {
    public MealNotFoundException(String message){
        super(message);
    }
    public MealNotFoundException(){
        super();
    }
}
