package com.example.backend.model;

import lombok.Data;

import java.util.List;

@Data
public class ProfileDto {

    private String email;
    private String name;
    private String gender;
    private String phone_number;
    private String dateOfBirth;
    private List<AddressDto> addresses;
    private List<ProductDto> product;

}
