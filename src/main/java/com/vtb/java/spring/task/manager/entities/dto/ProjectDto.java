package com.vtb.java.spring.task.manager.entities.dto;

import java.time.LocalDate;

public interface ProjectDto {
    Long getId();

    String getTitle();

    Long getLeaderId();

    String getLeaderUsername();

    LocalDate getDeadline();
}
