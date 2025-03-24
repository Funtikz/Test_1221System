package org.example.test_stajirovka.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.test_stajirovka.entity.Goal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private Long id;
    private String firstname;
    private String email;
    private Integer age;
    private Double weight;
    private Double height;
    private Goal goal;
}
