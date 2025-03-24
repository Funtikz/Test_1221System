package org.example.test_stajirovka.entity.enums;

import lombok.Getter;

@Getter
public enum NutrientType {
    PROTEIN("Белки"),
    FAT("Жиры"),
    CARBOHYDRATE("Углеводы");

    private final String description;

    NutrientType(String description) {
        this.description = description;
    }

}
