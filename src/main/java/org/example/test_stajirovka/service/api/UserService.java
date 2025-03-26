package org.example.test_stajirovka.service.api;

import org.example.test_stajirovka.dto.UserRequestDto;
import org.example.test_stajirovka.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findById(Long id);

    void createUser(UserRequestDto dto);

    void updateUser(Long id, UserRequestDto dto);

    void deleteUser(Long id);
}
