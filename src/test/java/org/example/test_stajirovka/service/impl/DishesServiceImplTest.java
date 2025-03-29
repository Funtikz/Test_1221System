package org.example.test_stajirovka.service.impl;

import org.example.test_stajirovka.dto.DishesRequestDto;
import org.example.test_stajirovka.entity.Dishes;
import org.example.test_stajirovka.entity.enums.NutrientType;
import org.example.test_stajirovka.exceptions.DishesNotFoundException;
import org.example.test_stajirovka.mappers.DishesMapper;
import org.example.test_stajirovka.repository.DishesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DishesServiceImplTest {

    @Mock
    private DishesRepository dishesRepository;

    @Mock
    private DishesMapper dishesMapper;

    @InjectMocks
    private DishesServiceImpl dishesService;

    private DishesRequestDto dishesRequestDto;
    private Dishes dish;

    @BeforeEach
    void setUp() {
        // Инициализация объектов перед каждым тестом
        dishesRequestDto = new DishesRequestDto();
        dishesRequestDto.setName("Salad");
        dishesRequestDto.setCalories(200.0);
        dishesRequestDto.setNutrientType(NutrientType.CARBOHYDRATE);

        dish = new Dishes();
        dish.setId(1L);
        dish.setName("Salad");
        dish.setCalories(200.0);
        dish.setNutrientType(NutrientType.CARBOHYDRATE);
    }

    @Test
    void findAll() {
        // Подготовка мока
        when(dishesRepository.findAll()).thenReturn(List.of(dish));

        // Вызов метода
        var result = dishesService.findAll();

        // Проверки
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Salad", result.get(0).getName());
        verify(dishesRepository, times(1)).findAll();  // Проверка вызова метода
    }

    @Test
    void findById() {
        // Подготовка мока
        when(dishesRepository.findById(1L)).thenReturn(Optional.of(dish));

        // Вызов метода
        Dishes result = dishesService.findById(1L);

        // Проверки
        assertNotNull(result);
        assertEquals("Salad", result.getName());
        verify(dishesRepository, times(1)).findById(1L);  // Проверка вызова метода
    }

    @Test
    void findByIdShouldThrowExceptionWhenNotFound() {
        // Подготовка мока
        when(dishesRepository.findById(1L)).thenReturn(Optional.empty());

        // Проверка исключения
        assertThrows(DishesNotFoundException.class, () -> dishesService.findById(1L));
        verify(dishesRepository, times(1)).findById(1L);  // Проверка вызова метода
    }

    @Test
    void createDishes() {
        // Подготовка мока
        when(dishesMapper.toEntityRequest(dishesRequestDto)).thenReturn(dish);

        // Вызов метода
        dishesService.createDishes(dishesRequestDto);

        // Проверки
        verify(dishesRepository, times(1)).save(dish);  // Проверка сохранения
    }

    @Test
    void updateDishes() {
        // Подготовка мока
        when(dishesRepository.findById(1L)).thenReturn(Optional.of(dish));

        // Обновление данных
        DishesRequestDto updatedDto = new DishesRequestDto();
        updatedDto.setName("Updated Salad");
        updatedDto.setCalories(250.0);
        updatedDto.setNutrientType(NutrientType.PROTEIN);

        // Вызов метода
        dishesService.updateDishes(1L, updatedDto);

        // Проверки
        assertEquals("Updated Salad", dish.getName());
        assertEquals(250.0, dish.getCalories());
        assertEquals(NutrientType.PROTEIN, dish.getNutrientType());
        verify(dishesRepository, times(1)).save(dish);  // Проверка сохранения
    }

    @Test
    void updateDishesShouldThrowExceptionWhenNotFound() {
        // Подготовка мока
        when(dishesRepository.findById(1L)).thenReturn(Optional.empty());

        // Проверка исключения
        assertThrows(DishesNotFoundException.class, () -> dishesService.updateDishes(1L, dishesRequestDto));
        verify(dishesRepository, times(1)).findById(1L);  // Проверка вызова метода
    }

    @Test
    void deleteDishes() {
        // Подготовка мока
        when(dishesRepository.findById(1L)).thenReturn(Optional.of(dish));

        // Вызов метода
        dishesService.deleteDishes(1L);

        // Проверки
        verify(dishesRepository, times(1)).deleteById(1L);  // Проверка удаления
    }

    @Test
    void deleteDishesShouldThrowExceptionWhenNotFound() {
        // Подготовка мока
        when(dishesRepository.findById(1L)).thenReturn(Optional.empty());

        // Проверка исключения
        assertThrows(DishesNotFoundException.class, () -> dishesService.deleteDishes(1L));
        verify(dishesRepository, times(1)).findById(1L);  // Проверка вызова метода
    }
}
