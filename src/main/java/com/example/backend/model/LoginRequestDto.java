package com.example.backend.model;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String email;
    private String password;
}
