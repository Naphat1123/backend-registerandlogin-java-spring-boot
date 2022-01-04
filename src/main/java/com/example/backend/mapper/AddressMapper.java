package com.example.backend.mapper;

import com.example.backend.entity.Address;
import com.example.backend.model.AddressDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {


    AddressDto toAddress(Address updateAddress);
}
