package org.example.test_stajirovka.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "dishes")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Dishes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "calories", nullable = false)
    private Double calories;

    @Enumerated(EnumType.STRING)
    @Column(name = "nutrient_type", nullable = false)
    private NutrientType nutrientType;
}
