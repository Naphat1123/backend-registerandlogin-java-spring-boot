package com.example.backend.repository;

import com.example.backend.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, String> {
    Page<Product> findByNameContainingIgnoreCase(String searchValue, Pageable pageable);

    Page<Product> findAll(Pageable pageable);

    List<Product> findByCatagory(String request);
}
