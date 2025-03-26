package org.example.test_stajirovka.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.test_stajirovka.entity.enums.MealType;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MealResponseDto {
    private Long id;
    private Long userId;
    private LocalDate localDate;
    private MealType mealType;
    private List<DishesResponseDto> dishesList;
}
