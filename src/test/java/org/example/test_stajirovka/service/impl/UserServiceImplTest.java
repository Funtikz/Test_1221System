package org.example.test_stajirovka.service.impl;

import org.example.test_stajirovka.dto.UserRequestDto;
import org.example.test_stajirovka.entity.User;
import org.example.test_stajirovka.entity.enums.Gender;
import org.example.test_stajirovka.entity.enums.Goal;
import org.example.test_stajirovka.exceptions.UserNotFoundException;
import org.example.test_stajirovka.mappers.UserMapper;
import org.example.test_stajirovka.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    private UserRequestDto userRequestDto;
    private User user;

    @BeforeEach
    void setUp() {
        userRequestDto = new UserRequestDto();
        userRequestDto.setFirstname("John");
        userRequestDto.setEmail("john@example.com");
        userRequestDto.setAge(25);
        userRequestDto.setWeight(70.0);
        userRequestDto.setHeight(175.0);
        userRequestDto.setGoal(Goal.WEIGHT_LOSS);

        user = new User();
        user.setId(1L);
        user.setFirstname("John");
        user.setEmail("john@example.com");
        user.setAge(25);
        user.setWeight(70.0);
        user.setHeight(175.0);
        user.setGoal(Goal.WEIGHT_LOSS);
        user.setGender(Gender.MALE);
    }

    @Test
    void findAll() {
        when(userRepository.findAll()).thenReturn(List.of(user));

        var result = userService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getFirstname());
        assertEquals(Goal.WEIGHT_LOSS, result.get(0).getGoal());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));

        User result = userService.findById(1L);

        assertNotNull(result);
        assertEquals("John", result.getFirstname());
        assertEquals(Goal.WEIGHT_LOSS, result.getGoal());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void findByIdShouldThrowExceptionWhenNotFound() {
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.findById(1L));
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void createUser() {
        when(userMapper.toEntityRequest(userRequestDto)).thenReturn(user);

        userService.createUser(userRequestDto);

        verify(userRepository, times(1)).save(user);
    }

    @Test
    void updateUser() {
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));

        userService.updateUser(1L, userRequestDto);

        assertEquals("John", user.getFirstname());
        assertEquals("john@example.com", user.getEmail());
        assertEquals(25, user.getAge());
        assertEquals(Goal.WEIGHT_LOSS, user.getGoal());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void updateUserShouldThrowExceptionWhenNotFound() {
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.updateUser(1L, userRequestDto));
        verify(userRepository, times(0)).save(user);
    }

    @Test
    void deleteUser() {
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));

        userService.deleteUser(1L);

        verify(userRepository, times(1)).delete(user);
    }

    @Test
    void deleteUserShouldThrowExceptionWhenNotFound() {
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.deleteUser(1L));
        verify(userRepository, times(0)).delete(user);
    }

    @Test
    void calorieIntakeForMale() {
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));

        user.setGender(Gender.MALE);
        user.setWeight(70.0);
        user.setHeight(175.0);
        user.setAge(25);

        double expectedCalories = 66 + 13.7 * 70.0 + 5 * 175.0 - 6.8 * 25;
        double result = userService.calorieIntake(1L);

        assertEquals(expectedCalories, result, 0.001);
    }

    @Test
    void calorieIntakeForFemale() {
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));

        user.setGender(Gender.FEMALE);
        user.setWeight(60.0);
        user.setHeight(165.0);
        user.setAge(30);

        double expectedCalories = 655 + 9.6 * 60.0 + 1.8 * 165.0 - 4.7 * 30;
        double result = userService.calorieIntake(1L);

        assertEquals(expectedCalories, result, 0.001);
    }
}
