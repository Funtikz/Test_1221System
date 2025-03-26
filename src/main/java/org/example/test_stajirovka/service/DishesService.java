package org.example.test_stajirovka.service;

import org.example.test_stajirovka.dto.DishesRequestDto;
import org.example.test_stajirovka.dto.DishesResponseDto;

import java.util.List;

public interface DishesService {
    List<DishesResponseDto> findAll();

    DishesResponseDto findById(Long id);

    void createDishes(DishesRequestDto dto);

    void updateDishes(Long id, DishesRequestDto dto);

    void deleteDishes(Long id);
}
