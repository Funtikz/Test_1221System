package org.example.test_stajirovka.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.test_stajirovka.dto.DishesRequestDto;
import org.example.test_stajirovka.entity.Dishes;
import org.example.test_stajirovka.exceptions.DishesNotFoundException;
import org.example.test_stajirovka.mappers.DishesMapper;
import org.example.test_stajirovka.repository.DishesRepository;
import org.example.test_stajirovka.service.api.DishesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DishesServiceImpl implements DishesService {

    private final DishesRepository dishesRepository;
    private final DishesMapper mapper;
    @Override
    public List<Dishes> findAll() {
        return dishesRepository.findAll().stream().toList();
    }

    @Override
    public Dishes findById(Long id) {
        return getById(id);
    }

    @Override
    @Transactional
    public void createDishes(DishesRequestDto dto) {
        dishesRepository.save(mapper.toEntityRequest(dto));
    }

    @Override
    @Transactional
    public void updateDishes(Long id, DishesRequestDto dto) {
        Dishes dishes = getById(id);
        dishes.setCalories(dto.getCalories());
        dishes.setName(dto.getName());
        dishes.setNutrientType(dto.getNutrientType());
        log.info("Блюдо с ID:" + id + "успешно обновлено!");
        dishesRepository.save(dishes);
    }

    @Override
    @Transactional
    public void deleteDishes(Long id) {
        Dishes dishes = getById(id);
        log.info("Блюдо с ID:" + id + "успешно удалено!");
        dishesRepository.deleteById(id);
    }

    private Dishes getById(Long id){
        Dishes dishes = dishesRepository.findById(id).orElseThrow(() -> {
            log.warn("Блюда с id:" + id + " не найдено");
            throw new DishesNotFoundException("Блюда с id:" + id + " не найдено");
        });
        return dishes;
    }
}
