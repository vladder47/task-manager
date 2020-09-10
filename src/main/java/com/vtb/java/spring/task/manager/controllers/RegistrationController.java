package com.vtb.java.spring.task.manager.controllers;

import com.vtb.java.spring.task.manager.entities.Task;
import com.vtb.java.spring.task.manager.entities.User;
import com.vtb.java.spring.task.manager.exceptions.ResourceNotFoundException;
import com.vtb.java.spring.task.manager.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.sql.SQLException;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/registration")
public class RegistrationController {
    private UserService userService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public User registerNewUser(@RequestBody User user) {
        if (user.getId() != null) {
            user.setId(null);
        }
        try {
            return userService.saveUser(user);
        } catch (RuntimeException e) {
            Throwable rootCause = com.google.common.base.Throwables.getRootCause(e);
            if (rootCause instanceof SQLException) {
                if ("23505".equals(((SQLException) rootCause).getSQLState())) {
                    System.out.println("Got the PSQLException!");
                }
            }
        }
        return new User();
    }
}

// TODO: 10.09.2020 Выводить PSQLException на фронт
