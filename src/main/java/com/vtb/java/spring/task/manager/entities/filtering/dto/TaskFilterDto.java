package com.vtb.java.spring.task.manager.entities.filtering.dto;

import com.vtb.java.spring.task.manager.entities.Task;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskFilterDto {
    private Long id;

    private String title;

    private String description;

    private UserDto leaderDto;

    private ProjectDto projectDto;

    private Task.Priority priority;

    private Task.Status status;

    private LocalDate deadline;
}
