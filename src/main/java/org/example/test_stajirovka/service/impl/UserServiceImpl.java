package org.example.test_stajirovka.service.impl;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.test_stajirovka.dto.UserRequestDto;
import org.example.test_stajirovka.entity.User;
import org.example.test_stajirovka.entity.enums.Gender;
import org.example.test_stajirovka.exceptions.UserNotFoundException;
import org.example.test_stajirovka.mappers.UserMapper;
import org.example.test_stajirovka.repository.UserRepository;
import org.example.test_stajirovka.service.api.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserMapper userMapper;


    @Override
    public List<User> findAll() {
        return repository.findAll()
                .stream()
                .toList();
    }

    @Override
    public User findById(Long id) {
        return getById(id);
    }

    @Override
    @Transactional
    public void createUser(UserRequestDto dto) {
        log.info("Пользователь успешно создан!");
        repository.save(userMapper.toEntityRequest(dto));
    }

    @Override
    @Transactional
    public void updateUser(Long id, UserRequestDto dto) {
        User existingUser = repository.findById(id).orElseThrow(() -> new UserNotFoundException("Пользователь не найден"));
        existingUser.setFirstname(dto.getFirstname());
        existingUser.setEmail(dto.getEmail());
        existingUser.setAge(dto.getAge());
        existingUser.setWeight(dto.getWeight());
        existingUser.setHeight(dto.getHeight());
        existingUser.setGoal(dto.getGoal());
        log.info("Пользователь с ID: " + id +" успешно обновлен!");
        repository.save(existingUser);
    }


    @Override
    @Transactional
    public void deleteUser(Long id) {
        log.info("Пользователь с ID: " + id +" успешно удален!");
        repository.delete(getById(id));
    }

    @Override
    public Double calorieIntake(Long userId) {
        User user = getById(userId);
        if (user.getGender().equals(Gender.MALE)){
            return  66 + 13.7 * user.getWeight() + 5 * user.getHeight() - 6.8 * user.getAge();
        }
        return  655 + 9.6 * user.getWeight() + 1.8 * user.getHeight() - 4.7 * user.getAge();
    }

    private User getById(Long id){
        User user = repository.findById(id).orElseThrow(() -> {
            log.warn("Пользователь с ID: " + id + " не найден");
            throw new UserNotFoundException("Пользователь с ID: " + id + " не найден");
        });
        return user;
    }
}
