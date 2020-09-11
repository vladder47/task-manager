package com.vtb.java.spring.task.manager.entities.dto;

import com.vtb.java.spring.task.manager.entities.Task;

import java.time.LocalDate;

// DTO для вывода всех тасок по определенному проекту
public interface TaskDto {
    Long getId();

    String getTitle();

    Long getLeaderId();

    String getLeaderUsername();

    String getDescription();

    Long getProjectId();

    String getProjectTitle();

    Task.Priority getPriority();

    Task.Status getStatus();

    LocalDate getDeadline();
}
