package com.hack.azure.mediknot.controller;

import com.hack.azure.mediknot.dto.UserDto;
import com.hack.azure.mediknot.entity.User;
import com.hack.azure.mediknot.exception.UserException;
import com.hack.azure.mediknot.mapper.UserMapper;
import com.hack.azure.mediknot.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        try {
            User user = userMapper.toEntity(userDto);
            user = userService.createUser(user);
            return ResponseEntity.ok(userMapper.toDto(user));
        }catch (UserException e){
            return ResponseEntity.status(e.getStatusCode())
        }
    }
}
