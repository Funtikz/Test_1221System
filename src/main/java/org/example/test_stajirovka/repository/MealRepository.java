package org.example.test_stajirovka.repository;

import org.example.test_stajirovka.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {
    List<Meal> findAllByUserId(Long userId);
    List<Meal> findAllByUserIdAndLocalDate(Long userId, LocalDate date);
    List<Meal> findByUserIdAndLocalDateBetween(Long userId, LocalDate startDate, LocalDate endDate);
}

