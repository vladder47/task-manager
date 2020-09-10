package com.vtb.java.spring.task.manager.entities.dto;

import java.time.LocalDateTime;

public interface CommentaryDto {
    Long getId();

    String getText();

    Long getParent();

    Long getTaskId();

    String getUsername();

    LocalDateTime getCreatedAt();

    Long getTotalCount();
}
