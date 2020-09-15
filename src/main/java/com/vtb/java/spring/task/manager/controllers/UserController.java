package com.vtb.java.spring.task.manager.controllers;

import com.vtb.java.spring.task.manager.entities.User;
import com.vtb.java.spring.task.manager.entities.dto.UserDto;
import com.vtb.java.spring.task.manager.exceptions.ResourceNotFoundException;
import com.vtb.java.spring.task.manager.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
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

    @GetMapping("/current")
    public UserDto getUserDtoByUsername(Principal principal) {
        return userService.findDtoByUsername(principal.getName())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Пользователь с логином = %s не найден", principal.getName())));
    }

    @GetMapping("/dtos/{username}")
    public UserDto getUserDtoByUsername(@PathVariable String username) {
        return userService.findDtoByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Пользователь с логином = %s не найден", username)));
    }

    @GetMapping("dtos/project/{id}")
    public List<UserDto> getAllUsersDtoByProjectId(@PathVariable Long id) {
        return userService.findAllUsersByProjectId(id);
    }

    @GetMapping("dtos/task/{id}")
    public List<UserDto> getAllUsersDtoByTaskId(@PathVariable Long id) {
        return userService.findAllUsersByTaskId(id);
    }

}
