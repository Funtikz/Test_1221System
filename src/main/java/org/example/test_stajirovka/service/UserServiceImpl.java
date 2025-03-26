package org.example.test_stajirovka.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.test_stajirovka.dto.UserRequestDto;
import org.example.test_stajirovka.dto.UserResponseDto;
import org.example.test_stajirovka.entity.User;
import org.example.test_stajirovka.exceptions.UserNotFoundException;
import org.example.test_stajirovka.mappers.UserMapper;
import org.example.test_stajirovka.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserMapper userMapper;


    @Override
    public List<UserResponseDto> findAll() {
        return repository.findAll().stream().map(userMapper::toDto).toList();
    }

    @Override
    public UserResponseDto findById(Long id) {
        return userMapper.toDto(getById(id));
    }

    @Override
    public void createUser(UserRequestDto dto) {
        repository.save(userMapper.toEntityRequest(dto));
    }

    @Override
    public void updateUser(Long id, UserRequestDto dto) {
        User existingUser = repository.findById(id).orElseThrow(() -> new UserNotFoundException("Пользователь не найден"));
        existingUser.setFirstname(dto.getFirstname());
        existingUser.setEmail(dto.getEmail());
        existingUser.setAge(dto.getAge());
        existingUser.setWeight(dto.getWeight());
        existingUser.setHeight(dto.getHeight());
        existingUser.setGoal(dto.getGoal());
        repository.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) {
        repository.delete(getById(id));
    }

    private User getById(Long id){
        User user = repository.findById(id).orElseThrow(() -> {
            log.error("Пользователь с ID: " + id + " не найден");
            throw new UserNotFoundException("Пользователь с ID: " + id + " не найден");
        });
        return user;
    }
}
