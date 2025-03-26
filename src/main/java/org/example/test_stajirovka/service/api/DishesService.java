package org.example.test_stajirovka.service.api;

import org.example.test_stajirovka.dto.DishesRequestDto;
import org.example.test_stajirovka.entity.Dishes;

import java.util.List;

public interface DishesService {
    List<Dishes> findAll();

    Dishes findById(Long id);

    void createDishes(DishesRequestDto dto);

    void updateDishes(Long id, DishesRequestDto dto);

    void deleteDishes(Long id);
}
