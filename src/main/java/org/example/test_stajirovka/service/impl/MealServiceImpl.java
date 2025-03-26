package org.example.test_stajirovka.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.test_stajirovka.dto.MealRequestDto;
import org.example.test_stajirovka.entity.Dishes;
import org.example.test_stajirovka.entity.Meal;
import org.example.test_stajirovka.exceptions.MealNotFound;
import org.example.test_stajirovka.repository.MealRepository;
import org.example.test_stajirovka.service.api.DishesService;
import org.example.test_stajirovka.service.api.MealService;
import org.example.test_stajirovka.service.api.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class MealServiceImpl implements MealService {
    private final MealRepository mealRepository;
    private final UserService userService;
    private final DishesService dishesService;

    @Override
    public List<Meal> getAllMyMeals(Long userId) {
        return mealRepository.findAllByUserId(userId)
                .stream()
                .toList();
    }

    @Override
    public List<Meal> getAllByDate(LocalDate dateTime, Long userId) {
        return mealRepository.findAllByUserIdAndLocalDate(userId, dateTime)
                .stream()
                .toList();
    }

    @Override
    @Transactional
    public void createMeal(MealRequestDto dto, Long userId) {
        List<Long> dishIds = dto.getDishIds();
        List<Dishes> allDishes = dishesService.findAll();
        List<Dishes> listDishes = allDishes.stream()
                .filter(dish -> dishIds.contains(dish.getId()))
                .collect(Collectors.toList());

        Meal meal = new Meal();
        meal.setMealType(dto.getMealType());
        meal.setUser(userService.findById(userId));
        meal.setDishes(listDishes);
        meal.setLocalDate(dto.getLocalDate());
        mealRepository.save(meal);
    }

    @Override
    @Transactional
    public void updateMeal(Long mealId, MealRequestDto dto) {
        Meal mealById = findMealById(mealId);
        List<Long> dishIds = dto.getDishIds();
        List<Dishes> allDishes = dishesService.findAll();
        List<Dishes> listDishes = allDishes.stream()
                .filter(dish -> dishIds.contains(dish.getId()))
                .collect(Collectors.toList());

        mealById.setLocalDate(dto.getLocalDate());
        mealById.setMealType(dto.getMealType());
        mealById.setDishes(listDishes);
        mealRepository.save(mealById);
    }

    @Override
    @Transactional
    public void deleteMeal(Long mealId) {
        Meal mealById = findMealById(mealId);
        mealRepository.delete(mealById);
    }

    private Meal findMealById(Long id){
         return mealRepository.findById(id).orElseThrow(() ->{
            log.error("Трапезы с ID:" + id + " не существует!");
            throw new MealNotFound("Трапезы с ID:" + id + " не существует!");
        });
    }
}
