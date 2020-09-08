package com.vtb.java.spring.task.manager.repositories;

import com.vtb.java.spring.task.manager.entities.User;
import com.vtb.java.spring.task.manager.entities.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u.id as id, u.username as username from User u")
    List<UserDto> findAllUsersDto();

    @Query("select u.id as id, u.username as username from User u " +
            "join u.projects p where p.id = :id")
    List<UserDto> findAllUsersByProjectId(Long id);

}
