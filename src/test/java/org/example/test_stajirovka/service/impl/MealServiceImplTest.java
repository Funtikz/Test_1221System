package org.example.test_stajirovka.service.impl;

import org.example.test_stajirovka.dto.MealRequestDto;
import org.example.test_stajirovka.entity.Dishes;
import org.example.test_stajirovka.entity.Meal;
import org.example.test_stajirovka.entity.enums.MealType;
import org.example.test_stajirovka.exceptions.MealNotFoundException;
import org.example.test_stajirovka.repository.MealRepository;
import org.example.test_stajirovka.service.api.DishesService;
import org.example.test_stajirovka.service.api.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MealServiceImplTest {

    @Mock
    private MealRepository mealRepository;

    @Mock
    private UserService userService;

    @Mock
    private DishesService dishesService;

    @InjectMocks
    private MealServiceImpl mealService;

    private MealRequestDto mealRequestDto;
    private Meal meal;
    private Dishes dish1, dish2;

    @BeforeEach
    void setUp() {
        mealRequestDto = new MealRequestDto();
        mealRequestDto.setMealType(MealType.BREAKFAST);
        mealRequestDto.setLocalDate(LocalDate.now());
        mealRequestDto.setDishIds(List.of(1L, 2L));

        dish1 = new Dishes();
        dish1.setId(1L);
        dish1.setName("Dish 1");

        dish2 = new Dishes();
        dish2.setId(2L);
        dish2.setName("Dish 2");

        meal = new Meal();
        meal.setId(1L);
        meal.setMealType(MealType.BREAKFAST);
        meal.setLocalDate(LocalDate.now());
        meal.setDishes(List.of(dish1, dish2));
    }

    @Test
    void getAllMyMeals() {
        when(mealRepository.findAllByUserId(1L)).thenReturn(List.of(meal));

        List<Meal> result = mealService.getAllMyMeals(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(MealType.BREAKFAST, result.get(0).getMealType());
        verify(mealRepository, times(1)).findAllByUserId(1L);
    }

    @Test
    void getAllByDate() {
        when(mealRepository.findAllByUserIdAndLocalDate(1L, LocalDate.now())).thenReturn(List.of(meal));

        List<Meal> result = mealService.getAllByDate(LocalDate.now(), 1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(LocalDate.now(), result.get(0).getLocalDate());
        verify(mealRepository, times(1)).findAllByUserIdAndLocalDate(1L, LocalDate.now());
    }

    @Test
    void createMeal() {
        when(dishesService.findAll()).thenReturn(List.of(dish1, dish2));
        when(userService.findById(1L)).thenReturn(new org.example.test_stajirovka.entity.User());

        mealService.createMeal(mealRequestDto, 1L);

        verify(mealRepository, times(1)).save(any(Meal.class));
    }

    @Test
    void updateMeal() {
        when(mealRepository.findById(1L)).thenReturn(Optional.of(meal));
        when(dishesService.findAll()).thenReturn(List.of(dish1, dish2));

        mealRequestDto.setMealType(MealType.LUNCH);
        mealRequestDto.setLocalDate(LocalDate.now().plusDays(1));

        mealService.updateMeal(1L, mealRequestDto);

        assertEquals(MealType.LUNCH, meal.getMealType());
        assertEquals(LocalDate.now().plusDays(1), meal.getLocalDate());
        verify(mealRepository, times(1)).save(meal);
    }

    @Test
    void deleteMeal() {
        when(mealRepository.findById(1L)).thenReturn(Optional.of(meal));

        mealService.deleteMeal(1L);

        verify(mealRepository, times(1)).delete(meal);
    }

    @Test
    void findMealByIdShouldThrowExceptionWhenNotFound() {
        when(mealRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(MealNotFoundException.class, () -> mealService.deleteMeal(1L));
        verify(mealRepository, times(1)).findById(1L);
    }
}
