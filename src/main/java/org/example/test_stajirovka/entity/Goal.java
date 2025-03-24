package org.example.test_stajirovka.entity;

public enum Goal {
    WEIGHT_LOSS("Похудение"),
    MAINTENANCE("Поддержание"),
    MUSCLE_GAIN("Набор массы");
    private final String description;

    Goal(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
}
