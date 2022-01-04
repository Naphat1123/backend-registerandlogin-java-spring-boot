package com.example.backend.service;

import com.example.backend.entity.Product;
import com.example.backend.entity.User;
import com.example.backend.exception.BaseException;
import com.example.backend.model.ProductRequest;
import com.example.backend.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    public Product createProduct(User user, ProductRequest request) throws BaseException {
        try {
            Product entity = new Product();
            entity.setUser(user);
            entity.setName(request.getName());
            entity.setPrice(request.getPrice());

            return productRepo.save(entity);
        } catch (Exception e) {
            throw new BaseException("can't not create product");
        }
    }

    public Optional<Product> findById(String id) {
        return productRepo.findById(id);
    }

    public Product updateProduct(ProductRequest productDto) throws BaseException {
        try {
            Product entity = productRepo.findById(productDto.getId()).get();

            entity.setName(productDto.getName());
            entity.setPrice(productDto.getPrice());

            return productRepo.save(entity);
        } catch (Exception e) {
            throw new BaseException("product can't update");
        }
    }

    public void deleteProduct(String id) {
        productRepo.deleteById(id);
    }

//    public Product updateProduct(User user, String id) throws BaseException {
//        Optional<Product> productOptional = productRepo.findById(id);
//        Product product = productOptional.get();
//
//
//    }
}
