package com.example.backend.service;

import com.example.backend.entity.Product;
import com.example.backend.entity.User;
import com.example.backend.exception.BaseException;
import com.example.backend.mapper.ProductMappper;
import com.example.backend.model.ProductRequest;
import com.example.backend.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ProductMappper productMappper;

    public Product createProduct(User user, ProductRequest request) throws BaseException {
        try {
            Product entity = new Product();
            entity.setUser(user);
            entity.setName(request.getName());
            entity.setPrice(request.getPrice());
            entity.setCatagory(request.getCatagory_name());

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

    public List<Product> findAll() {
        return productRepo.findAll();
    }

    public Page<Product> getSearchProduct(String searchValue, Pageable pageable) {

        return productRepo.findByNameContainingIgnoreCase(searchValue, pageable);

    }

    public Page<Product> findAllProduct(Pageable pageable) {
        return productRepo.findAll(pageable);
    }

    public List<Product> findByCatagory(String request) {
        return productRepo.findByCatagory(request);
    }
}
