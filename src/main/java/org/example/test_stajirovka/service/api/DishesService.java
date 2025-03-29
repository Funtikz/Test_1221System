package org.example.test_stajirovka.service.api;

import org.example.test_stajirovka.dto.DishesRequestDto;
import org.example.test_stajirovka.entity.Dishes;

import java.util.List;

public interface DishesService {
    /**
     * Получает список всех блюд.
     *
     * @return список всех блюд
     */
    List<Dishes> findAll();

    /**
     * Находит блюдо по идентификатору.
     *
     * @param id идентификатор блюда
     * @return найденное блюдо
     * @throws org.example.test_stajirovka.exceptions.DishesNotFoundException если блюдо не найдено
     */
    Dishes findById(Long id);

    /**
     * Создаёт новое блюдо.
     *
     * @param dto данные для создания блюда
     */
    void createDishes(DishesRequestDto dto);

    /**
     * Обновляет существующее блюдо.
     *
     * @param id  идентификатор блюда
     * @param dto данные для обновления блюда
     * @throws org.example.test_stajirovka.exceptions.DishesNotFoundException если блюдо не найдено
     */
    void updateDishes(Long id, DishesRequestDto dto);

    /**
     * Удаляет блюдо по идентификатору.
     *
     * @param id идентификатор блюда
     * @throws org.example.test_stajirovka.exceptions.DishesNotFoundException если блюдо не найдено
     */
    void deleteDishes(Long id);
}
