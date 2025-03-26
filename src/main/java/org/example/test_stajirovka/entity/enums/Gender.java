package org.example.test_stajirovka.entity.enums;

import lombok.Getter;

@Getter
public enum Gender {
    MALE("Мужчина"),
    FEMALE("Женшина");
    private final String description;

    Gender(String description) {
        this.description = description;
    }

}
