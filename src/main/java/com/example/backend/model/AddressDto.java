package com.example.backend.model;

import lombok.Data;

@Data
public class AddressDto {

    private String id;
    private String address;
    private String city;
    private String zipcode;
}
