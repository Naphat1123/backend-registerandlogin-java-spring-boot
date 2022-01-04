package com.example.backend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "m_address")
public class Address extends BaseEntity {

    @Column(length = 255)
    private String address;

    @Column(length = 60)
    private String city;

    @Column(length = 30)
    private String zipcode;

    @ManyToOne
    @JoinColumn(name = "m_user_id")
    private User user;
}
