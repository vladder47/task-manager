package com.vtb.java.spring.task.manager.entities.dto;

import java.time.LocalDateTime;

public interface FileDto {
    Long getId();

    String getFileName();

    LocalDateTime getCreatedAt();

    LocalDateTime getUpdatedAt();
}
