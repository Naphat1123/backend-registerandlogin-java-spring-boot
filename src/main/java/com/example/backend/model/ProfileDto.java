package com.example.backend.model;

import lombok.Data;

import java.util.List;

@Data
public class ProfileDto {

    private String email;
    private String name;
    private List<ProductDto> product;

}
