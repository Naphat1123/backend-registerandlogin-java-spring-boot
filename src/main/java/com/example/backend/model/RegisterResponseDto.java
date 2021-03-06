package com.example.backend.model;

import lombok.Data;

@Data
public class RegisterResponseDto {

    private String email;

    private String name;

    private String gender;

    private String phone_number;

    private String dateOfBirth;
}
