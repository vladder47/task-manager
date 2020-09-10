package com.vtb.java.spring.task.manager.entities.dto;

import com.vtb.java.spring.task.manager.entities.User;

import java.time.LocalDate;
import java.util.List;

public interface ProjectDto {
    Long getId();

    String getTitle();

    String getLeaderUsername();

    List<User> getUsers();

    LocalDate getCreatedAt();

    LocalDate getDeadline();
}
