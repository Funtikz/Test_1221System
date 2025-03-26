package org.example.test_stajirovka.mappers;

import org.example.test_stajirovka.dto.DishesRequestDto;
import org.example.test_stajirovka.dto.DishesResponseDto;
import org.example.test_stajirovka.entity.Dishes;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DishesMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "calories", target = "calories")
    @Mapping(source = "nutrient_type", target = "nutrient_type")
    Dishes toEntityRequest(DishesRequestDto dto);

    DishesResponseDto toDto(Dishes entity);
}
