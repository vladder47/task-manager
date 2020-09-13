package com.vtb.java.spring.task.manager.services;

import com.vtb.java.spring.task.manager.entities.Notification;
import com.vtb.java.spring.task.manager.entities.Role;
import com.vtb.java.spring.task.manager.entities.User;
import com.vtb.java.spring.task.manager.entities.dto.UserDto;
import com.vtb.java.spring.task.manager.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
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

    public List<UserDto> findAllUsersByTaskId(Long id) {
        return userRepository.findAllUsersByTaskId(id);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<UserDto> findDtoByUsername(String username) {
        return userRepository.findDtoByUsername(username);
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    public void setNotification(Notification notification, Long id){
        User user = userRepository.findById(id).get();
        user.getNotifications().add(notification);
    }
}
