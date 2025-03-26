package org.example.test_stajirovka.service;

import org.example.test_stajirovka.dto.UserRequestDto;
import org.example.test_stajirovka.dto.UserResponseDto;

import java.util.List;

public interface UserService {
    List<UserResponseDto> findAll();

    UserResponseDto findById(Long id);

    void createUser(UserRequestDto dto);

    void updateUser(Long id, UserRequestDto dto);

    void deleteUser(Long id);
}
