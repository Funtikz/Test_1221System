package org.example.test_stajirovka.mappers;

import org.example.test_stajirovka.dto.UserRequestDto;
import org.example.test_stajirovka.dto.UserResponseDto;
import org.example.test_stajirovka.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "firstname", target = "firstname")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "age", target = "age")
    @Mapping(source = "weight", target = "weight")
    @Mapping(source = "height", target = "height")
    @Mapping(source = "goal", target = "goal")
    @Mapping(source = "gender", target = "gender")
    User toEntityRequest(UserRequestDto userRequestDto);

    User toEntity(UserResponseDto dto);
    UserResponseDto toDto(User user);
}