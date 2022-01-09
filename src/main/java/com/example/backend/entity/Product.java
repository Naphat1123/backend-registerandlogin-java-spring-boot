package com.example.backend.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity(name = "m_product")
public class Product extends BaseEntity {

    @Column(nullable = false, length = 60)
    private String name;

    @Column(nullable = false, length = 60)
    private int price;

    @ManyToOne
    @JoinColumn(name = "m_product_category")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "m_user_id")
    private User user;
}
