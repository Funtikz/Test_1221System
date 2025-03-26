package org.example.test_stajirovka.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.test_stajirovka.entity.enums.Gender;
import org.example.test_stajirovka.entity.enums.NutrientType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DishesResponseDto {
    private Long id;
    private String name;
    private Double calories;
    private NutrientType nutrientType;
    private Gender gender;

}
