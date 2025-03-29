package org.example.test_stajirovka.service.impl;

import org.example.test_stajirovka.dto.DishesRequestDto;
import org.example.test_stajirovka.entity.Dishes;
import org.example.test_stajirovka.entity.enums.NutrientType;
import org.example.test_stajirovka.exceptions.DishesNotFoundException;
import org.example.test_stajirovka.mappers.DishesMapper;
import org.example.test_stajirovka.repository.DishesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
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
        when(dishesRepository.findAll()).thenReturn(List.of(dish));

        var result = dishesService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Salad", result.get(0).getName());
        verify(dishesRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        when(dishesRepository.findById(1L)).thenReturn(Optional.of(dish));

        Dishes result = dishesService.findById(1L);

        assertNotNull(result);
        assertEquals("Salad", result.getName());
        verify(dishesRepository, times(1)).findById(1L);
    }

    @Test
    void findByIdShouldThrowExceptionWhenNotFound() {
        when(dishesRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(DishesNotFoundException.class, () -> dishesService.findById(1L));
        verify(dishesRepository, times(1)).findById(1L);
    }

    @Test
    void createDishes() {
        when(dishesMapper.toEntityRequest(dishesRequestDto)).thenReturn(dish);

        dishesService.createDishes(dishesRequestDto);

        verify(dishesRepository, times(1)).save(dish);
    }

    @Test
    void updateDishes() {
        when(dishesRepository.findById(1L)).thenReturn(Optional.of(dish));

        DishesRequestDto updatedDto = new DishesRequestDto();
        updatedDto.setName("Updated Salad");
        updatedDto.setCalories(250.0);
        updatedDto.setNutrientType(NutrientType.PROTEIN);

        dishesService.updateDishes(1L, updatedDto);

        assertEquals("Updated Salad", dish.getName());
        assertEquals(250.0, dish.getCalories());
        assertEquals(NutrientType.PROTEIN, dish.getNutrientType());
        verify(dishesRepository, times(1)).save(dish);
    }

    @Test
    void updateDishesShouldThrowExceptionWhenNotFound() {
        when(dishesRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(DishesNotFoundException.class, () -> dishesService.updateDishes(1L, dishesRequestDto));
        verify(dishesRepository, times(1)).findById(1L);
    }

    @Test
    void deleteDishes() {
        when(dishesRepository.findById(1L)).thenReturn(Optional.of(dish));

        dishesService.deleteDishes(1L);

        verify(dishesRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteDishesShouldThrowExceptionWhenNotFound() {
        when(dishesRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(DishesNotFoundException.class, () -> dishesService.deleteDishes(1L));
        verify(dishesRepository, times(1)).findById(1L);
    }
}
