package org.example.test_stajirovka.service.impl;

import org.example.test_stajirovka.dto.DailyReportDto;
import org.example.test_stajirovka.entity.Dishes;
import org.example.test_stajirovka.entity.Meal;
import org.example.test_stajirovka.entity.enums.MealType;
import org.example.test_stajirovka.repository.MealRepository;
import org.example.test_stajirovka.service.api.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReportServiceImplTest {

    @Mock
    private MealRepository mealRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private ReportServiceImpl reportService;

    private Meal meal1, meal2;
    private Dishes dish1, dish2;
    private LocalDate date;

    @BeforeEach
    void setUp() {
        date = LocalDate.now();

        dish1 = new Dishes();
        dish1.setCalories(300.0);

        dish2 = new Dishes();
        dish2.setCalories(500.0);

        meal1 = new Meal();
        meal1.setMealType(MealType.BREAKFAST);
        meal1.setLocalDate(date);
        meal1.setDishes(List.of(dish1));

        meal2 = new Meal();
        meal2.setMealType(MealType.LUNCH);
        meal2.setLocalDate(date);
        meal2.setDishes(List.of(dish2));
    }

    @Test
    void getDailyReport() {
        when(mealRepository.findAllByUserIdAndLocalDate(1L, date)).thenReturn(List.of(meal1, meal2));

        DailyReportDto result = reportService.getDailyReport(1L, date);

        assertNotNull(result);
        assertEquals(1L, result.getUserId());
        assertEquals(date, result.getDate());
        assertEquals(800, result.getTotalCalories(), 0.001);
        assertEquals(2, result.getMeals().size());
        verify(mealRepository, times(1)).findAllByUserIdAndLocalDate(1L, date);
    }

    @Test
    void isCalorieLimitExceeded() {
        when(mealRepository.findAllByUserIdAndLocalDate(1L, date)).thenReturn(List.of(meal1, meal2));
        when(userService.calorieIntake(1L)).thenReturn(700.0);

        boolean result = reportService.isCalorieLimitExceeded(1L, date);

        assertTrue(result);
        verify(mealRepository, times(1)).findAllByUserIdAndLocalDate(1L, date);
    }

    @Test
    void isCalorieLimitNotExceeded() {
        when(mealRepository.findAllByUserIdAndLocalDate(1L, date)).thenReturn(List.of(meal1, meal2));
        when(userService.calorieIntake(1L)).thenReturn(1000.0);

        boolean result = reportService.isCalorieLimitExceeded(1L, date);

        assertFalse(result);
        verify(mealRepository, times(1)).findAllByUserIdAndLocalDate(1L, date);
    }

    @Test
    void getMealHistory() {
        LocalDate startDate = LocalDate.now().minusDays(5);
        LocalDate endDate = LocalDate.now();
        when(mealRepository.findByUserIdAndLocalDateBetween(1L, startDate, endDate)).thenReturn(List.of(meal1, meal2));

        List<Meal> result = reportService.getMealHistory(1L, startDate, endDate);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(meal1));
        assertTrue(result.contains(meal2));
        verify(mealRepository, times(1)).findByUserIdAndLocalDateBetween(1L, startDate, endDate);
    }
}
