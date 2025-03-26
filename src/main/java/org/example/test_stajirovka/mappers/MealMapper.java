package org.example.test_stajirovka.mappers;

import org.example.test_stajirovka.dto.MealRequestDto;
import org.example.test_stajirovka.dto.MealResponseDto;
import org.example.test_stajirovka.entity.Dishes;
import org.example.test_stajirovka.entity.Meal;
import org.example.test_stajirovka.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface MealMapper {

    @Mapping(target = "dishes", source = "dishIds")
    @Mapping(source = "localDate", target = "localDate")
    @Mapping(source = "mealType", target = "mealType")
    Meal toEntity(MealRequestDto dto);

    @Mapping(source = "mealType", target = "mealType")
    @Mapping(source = "localDate", target = "localDate")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "dishes", target = "dishesList")
    MealResponseDto toDto(Meal entity);

    default Long userToUserId(User user) {
        if (user == null) {
            return null;
        }
        return user.getId();
    }
    @Named("mapDishIdsToDishes")
    default List<Dishes> mapDishIdsToDishes(List<Long> dishIds) {
        if (dishIds == null || dishIds.isEmpty()) {
            return Collections.emptyList();
        }

        return dishIds.stream()
                .map(this::getDishesById)
                .collect(Collectors.toList());
    }
    Dishes getDishesById(Long id);

}
