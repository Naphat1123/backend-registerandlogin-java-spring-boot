package com.example.backend.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity(name = "category")
public class Category {

    @Id
    @Column(length = 36, nullable = false, updatable = false)
    private String code;

    @Column(nullable = false, length = 60)
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Product> products;

}
