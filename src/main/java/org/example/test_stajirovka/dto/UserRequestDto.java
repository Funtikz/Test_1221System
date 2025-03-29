package org.example.test_stajirovka.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.test_stajirovka.entity.enums.Gender;
import org.example.test_stajirovka.entity.enums.Goal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    @NotBlank(message = "Имя не может быть пустым")
    @Pattern(regexp = "^[A-Za-zА-Яа-яЁё\\s-]+$", message = "Имя должно содержать только буквы")
    private String firstname;

    @Email(message = "Некорректный email")
    @NotBlank(message = "Email не может быть пустым")
    private String email;

    @NotNull(message = "Возраст обязателен")
    @Min(value = 1, message = "Возраст должен быть больше 0")
    @Max(value = 133, message = "Вам не может быть больше 133 :) ")
    private Integer age;

    @NotNull(message = "Вес обязателен")
    @Min(value = 1, message = "Вес должен быть больше 0")
    private Double weight;

    @NotNull(message = "Гендер не может быть пустой")
    private Gender gender;


    @NotNull(message = "Рост обязателен")
    @Min(value = 50, message = "Рост должен быть больше 50 см")
    private Double height;
    @NotNull(message = "Цель обязательна (Похудение, Поддержание, Набор массы)")
    private Goal goal;
}
