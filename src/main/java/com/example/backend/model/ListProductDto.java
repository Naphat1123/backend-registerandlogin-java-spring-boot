package com.example.backend.model;

import lombok.Data;

import java.util.List;

@Data
public class ListProductDto {

    private List<ProductDto> productList;
    private int count;

}
