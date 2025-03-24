package org.example.test_stajirovka.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.test_stajirovka.entity.enums.MealType;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MealRequestDto {
    private LocalDateTime mealTime;
    private MealType mealType;
    private List<Long> dishIds;
}
