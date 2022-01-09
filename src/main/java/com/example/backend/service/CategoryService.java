package com.example.backend.service;

import com.example.backend.entity.Category;
import com.example.backend.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    public Optional<Category> findByCode(String code) {
        return categoryRepo.findByCode(code);
    }

}
