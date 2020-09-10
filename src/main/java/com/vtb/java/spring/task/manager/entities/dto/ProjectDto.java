package com.vtb.java.spring.task.manager.entities.dto;

import com.vtb.java.spring.task.manager.entities.User;

import java.time.LocalDate;

public interface ProjectDto {
    Long getId();

    String getTitle();

    Long getLeaderId();

    String getLeaderUsername();

    LocalDate getDeadline();
}
