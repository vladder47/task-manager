package com.vtb.java.spring.task.manager.controllers;

import com.vtb.java.spring.task.manager.entities.User;
import com.vtb.java.spring.task.manager.entities.dto.UserDto;
import com.vtb.java.spring.task.manager.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/dtos")
    public List<UserDto> getAllUsersDto() {
        return userService.findAllUsersDto();
    }

    @GetMapping("dtos/project/{id}")
    public List<UserDto> getAllUsersDtoByProjectId(@PathVariable Long id) {
        return userService.findAllUsersByProjectId(id);
    }
}
