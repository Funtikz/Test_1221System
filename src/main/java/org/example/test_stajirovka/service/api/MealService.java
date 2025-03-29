package org.example.test_stajirovka.service.api;

import org.example.test_stajirovka.dto.MealRequestDto;
import org.example.test_stajirovka.entity.Meal;

import java.time.LocalDate;
import java.util.List;

public interface MealService {
    /**
     * Получает список всех приёмов пищи пользователя.
     *
     * @param userId ID пользователя
     * @return список приёмов пищи
     */
    List<Meal> getAllMyMeals(Long userId);

    /**
     * Получает список всех приёмов пищи пользователя за указанную дату.
     *
     * @param dateTime Дата
     * @param userId   ID пользователя
     * @return список приёмов пищи
     */
    List<Meal> getAllByDate(LocalDate dateTime, Long userId);

    /**
     * Создаёт новый приём пищи.
     *
     * @param dto    DTO с данными о приёме пищи
     * @param userId ID пользователя
     */
    void createMeal(MealRequestDto dto, Long userId);

    /**
     * Обновляет данные приёма пищи.
     *
     * @param mealId ID приёма пищи
     * @param dto    DTO с обновлёнными данными
     */
    void updateMeal(Long mealId, MealRequestDto dto);

    /**
     * Удаляет приём пищи по ID.
     *
     * @param mealId ID приёма пищи
     */
    void deleteMeal(Long mealId);

}
