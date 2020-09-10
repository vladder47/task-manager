package com.vtb.java.spring.task.manager.entities.dto;

import lombok.Data;

@Data
public class JwtRequest {
    private String username;
    private String password;
}
