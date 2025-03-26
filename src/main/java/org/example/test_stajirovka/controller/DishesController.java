package org.example.test_stajirovka.controller;

import lombok.RequiredArgsConstructor;
import org.example.test_stajirovka.dto.DishesRequestDto;
import org.example.test_stajirovka.dto.DishesResponseDto;
import org.example.test_stajirovka.service.DishesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("dishes")
@RequiredArgsConstructor
public class DishesController {
    private final DishesService dishesService;

    @GetMapping("get-all")
    public ResponseEntity<List<DishesResponseDto>> findAll(){
        return new ResponseEntity<>(dishesService.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<DishesResponseDto> findById(@PathVariable("id") Long id){
        return new ResponseEntity<>(dishesService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createDishes(@RequestBody DishesRequestDto dto){
        dishesService.createDishes(dto);
        return new ResponseEntity<>("Блюдо успешно создано!", HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateDishes(@PathVariable("id") Long id,
                                               @RequestBody DishesRequestDto dto){
        dishesService.updateDishes(id, dto);
        return new ResponseEntity<>("Блюдо успешно обновлено", HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteDishes(@PathVariable("id") Long id){
        dishesService.deleteDishes(id);
        return new ResponseEntity<>("Блюдо успешно удалено", HttpStatus.OK);
    }

}
