package org.example.test_stajirovka.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.test_stajirovka.dto.DailyReportDto;
import org.example.test_stajirovka.entity.Meal;
import org.example.test_stajirovka.repository.MealRepository;
import org.example.test_stajirovka.service.api.ReportService;
import org.example.test_stajirovka.service.api.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReportServiceImpl implements ReportService {
    private final MealRepository mealRepository;
    private final UserService userService;

    @Override
    public DailyReportDto getDailyReport(Long userId, LocalDate date) {
        List<Meal> meals = mealRepository.findAllByUserIdAndLocalDate(userId, date);
        double totalCalories = meals.stream().mapToDouble(Meal::getTotalCalories).sum();

        return new DailyReportDto(userId, date, totalCalories, meals);
    }

    @Override
    public boolean isCalorieLimitExceeded(Long userId, LocalDate date) {
        double dailyLimit = userService.calorieIntake(userId);
        double consumedCalories = getDailyReport(userId, date).getTotalCalories();
        return consumedCalories > dailyLimit;
    }

    @Override
    public List<Meal> getMealHistory(Long userId, LocalDate startDate, LocalDate endDate) {
        return mealRepository.findByUserIdAndLocalDateBetween(userId, startDate, endDate);
    }
}
