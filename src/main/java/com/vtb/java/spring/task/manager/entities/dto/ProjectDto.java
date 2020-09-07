package com.vtb.java.spring.task.manager.entities.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface ProjectDto {
    Long getId();

    String getTitle();

    String getLeaderUsername();

    LocalDate getCreatedAt();

    LocalDateTime getDeadline();
}
