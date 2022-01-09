package com.example.backend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "m_product")
public class Product extends BaseEntity {

    @Column(nullable = false, length = 60)
    private String name;

    @Column(nullable = false, length = 60)
    private int price;

    @Column(nullable = false, length = 60)
    private String catagory;

    @ManyToOne
    @JoinColumn(name = "m_user_id")
    private User user;
}
