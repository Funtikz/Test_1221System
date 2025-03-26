package org.example.test_stajirovka.exceptions;

public class MealNotFound extends RuntimeException {
    public MealNotFound (String message){
        super(message);
    }
    public MealNotFound (){
        super();
    }
}
