package org.example.test_stajirovka.service.api;

import org.example.test_stajirovka.dto.UserRequestDto;
import org.example.test_stajirovka.entity.User;

import java.util.List;

public interface UserService {

    /**
     * Получить список всех пользователей.
     *
     * @return список всех пользователей
     */
    List<User> findAll();

    /**
     * Получить пользователя по его идентификатору.
     *
     * @param userId идентификатор пользователя
     * @return пользователь с указанным идентификатором
     */
    User findById(Long userId);

    /**
     * Создать нового пользователя.
     *
     * @param dto данные пользователя для создания
     */
    void createUser(UserRequestDto dto);

    /**
     * Обновить данные пользователя.
     *
     * @param userId идентификатор пользователя, чьи данные нужно обновить
     * @param dto новые данные пользователя
     */
    void updateUser(Long userId, UserRequestDto dto);

    /**
     * Удалить пользователя по его идентификатору.
     *
     * @param userId идентификатор пользователя, которого нужно удалить
     */
    void deleteUser(Long userId);

    /**
     * Получить расчетное количество калорий, которое должен потребить пользователь в день.
     *
     * @param userId идентификатор пользователя, для которого необходимо рассчитать суточную норму калорий
     * @return количество калорий, которое необходимо потребить пользователю
     */
    Double calorieIntake(Long userId);
}
