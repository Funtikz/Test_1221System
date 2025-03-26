package org.example.test_stajirovka.controller;

import lombok.RequiredArgsConstructor;
import org.example.test_stajirovka.dto.MealRequestDto;
import org.example.test_stajirovka.dto.MealResponseDto;
import org.example.test_stajirovka.mappers.MealMapper;
import org.example.test_stajirovka.service.api.MealService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("meal")
@RequiredArgsConstructor
public class MealController {
    private final MealService mealService;
    private final MealMapper mealMapper;

    @GetMapping("my-meals/{userId}")
    public ResponseEntity<List<MealResponseDto>> getAllMyMeals(@PathVariable("userId") Long userId){
        List<MealResponseDto> list = mealService.getAllMyMeals(userId).
                stream()
                .map(mealMapper::toDto)
                .toList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("my-meals-by-date/{userId}")
    public ResponseEntity<List<MealResponseDto>> getAllMyMealsByDate(@PathVariable("userId") Long userId,
                                                                     @RequestParam("date")LocalDate date){
        List<MealResponseDto> list = mealService.getAllByDate(date,userId)
                .stream()
                .map(mealMapper::toDto)
                .toList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("{userId}")
    public ResponseEntity<String> createMeal(@PathVariable("userId") Long userId,
                                             @RequestBody MealRequestDto dto){
        mealService.createMeal(dto, userId);
        return new ResponseEntity<>("Трапеза успешно создана", HttpStatus.CREATED);
    }

    @PutMapping("{mealId}")
    public ResponseEntity<String> updateMeal(@PathVariable("mealId") Long mealId,
                                             @RequestBody MealRequestDto dto){
        mealService.updateMeal(mealId, dto);
        return new ResponseEntity<>("Трапеза успешно обновлена!", HttpStatus.OK);
    }

    @DeleteMapping("{mealId}")
    public ResponseEntity<String> deleteMeal(@PathVariable("mealId") Long mealId){
        mealService.deleteMeal(mealId);
        return new ResponseEntity<>("Трапеза успешно удалена!", HttpStatus.OK);
    }


}
