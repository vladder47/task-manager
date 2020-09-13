package com.vtb.java.spring.task.manager.repositories;

import com.vtb.java.spring.task.manager.entities.User;
import com.vtb.java.spring.task.manager.entities.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u.id as id, u.username as username from User u")
    List<UserDto> findAllUsersDto();

    Optional<User> findByUsername(String username);

    @Query("select u.id as id, u.username as username from User u" +
            " where u.username = :username")
    Optional<UserDto> findDtoByUsername(String username);

    @Query("select u.id as id, u.username as username from User u " +
            "join u.projects p where p.id = :id")
    List<UserDto> findAllUsersByProjectId(Long id);

    @Query("select u.id as id, u.username as username from User u " +
            "join u.tasks t where t.id = :id")
    List<UserDto> findAllUsersByTaskId(Long id);

    @Query("select u.id as id, u.username as username from User u" +
            " where u.username = :username")
    Optional<UserDto> findDtoByUsername(String username);


}
