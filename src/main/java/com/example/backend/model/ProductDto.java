package com.example.backend.model;

import lombok.Data;

@Data
public class ProductDto {

    private String id;

    private String name;

    private int price;

    private CategoryDto category;
}
