package com.example.backend.model;

import lombok.Data;

@Data
public class ProductRequest {

    private String id;

    private String name;

    private int price;

    private String catagory_name;


}
