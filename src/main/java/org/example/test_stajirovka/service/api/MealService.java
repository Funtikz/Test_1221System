package org.example.test_stajirovka.service.api;

import org.example.test_stajirovka.dto.MealRequestDto;
import org.example.test_stajirovka.entity.Meal;

import java.time.LocalDate;
import java.util.List;

public interface MealService {
    List<Meal> getAllMyMeals(Long userId);
    List<Meal> getAllByDate(LocalDate dateTime, Long userId);
    void createMeal (MealRequestDto dto, Long userId);

    void updateMeal(Long mealId, MealRequestDto dto);

    void deleteMeal(Long mealId);

}
