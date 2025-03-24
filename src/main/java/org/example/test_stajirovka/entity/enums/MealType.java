package org.example.test_stajirovka.entity.enums;

import lombok.Getter;

@Getter
public enum MealType {
    BREAKFAST("Завтрак"),
    LUNCH("Обед"),
    DINNER("Ужин"),
    SNACK("Перекус");

    private final String description;

    MealType(String description){
        this.description = description;
    }

}
