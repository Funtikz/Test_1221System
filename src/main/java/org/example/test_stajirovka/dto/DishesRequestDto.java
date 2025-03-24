package org.example.test_stajirovka.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.test_stajirovka.entity.NutrientType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DishesRequestDto {
    @NotBlank(message = "Название блюда не может быть пустым")
    private String name;

    @NotNull(message = "Количество калорий обязательно")
    @Positive(message = "Количество калорий должно быть положительным")
    private Double calories;

    @NotNull(message = "Тип нутриента обязателен (Белки/Жиры/Углеводы)")
    private NutrientType nutrientType;
}
