package com.vtb.java.spring.task.manager.exceptions;

import lombok.Data;

import java.util.Date;

@Data
public class  TaskManagerServiceError {
    private int status;
    private String message;
    private Date timestamp;

    public TaskManagerServiceError(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = new Date();
    }
}
