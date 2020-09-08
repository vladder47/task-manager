package com.vtb.java.spring.task.manager.services;

import com.vtb.java.spring.task.manager.entities.User;
import com.vtb.java.spring.task.manager.entities.dto.UserDto;
import com.vtb.java.spring.task.manager.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public List<UserDto> findAllUsersDto() {
        return userRepository.findAllUsersDto();
    }

    public List<UserDto> findAllUsersByProjectId(Long id) {
        return userRepository.findAllUsersByProjectId(id);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

}
