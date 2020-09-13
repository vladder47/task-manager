package com.vtb.java.spring.task.manager.entities.dto;

import java.time.LocalDateTime;

public interface NotificationDto {
    Long getId();

    String getText();

    LocalDateTime getCreatedAt();
}
