package org.example.test_stajirovka.controller;

import lombok.RequiredArgsConstructor;
import org.example.test_stajirovka.dto.UserRequestDto;
import org.example.test_stajirovka.dto.UserResponseDto;
import org.example.test_stajirovka.mappers.UserMapper;
import org.example.test_stajirovka.service.api.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    @GetMapping("get-all")
    public ResponseEntity<List<UserResponseDto>> findAll(){
        List<UserResponseDto> list = userService.findAll().stream().map(userMapper::toDto).toList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable("id") Long id){
        return new ResponseEntity<>(userMapper.toDto(userService.findById(id)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserRequestDto dto){
        userService.createUser(dto);
        return new ResponseEntity<>("Пользователь создан!", HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateUser(@PathVariable("id") Long id, @RequestBody UserRequestDto dto) {
        userService.updateUser(id, dto);
        return new ResponseEntity<>("Пользователь обновлен", HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>("Пользователь удален", HttpStatus.OK);
    }

}
