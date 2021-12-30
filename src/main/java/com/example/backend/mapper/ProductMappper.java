package com.example.backend.mapper;

import com.example.backend.entity.Product;
import com.example.backend.model.ProductDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMappper {

    ProductDto toProduct(Product product);
}
