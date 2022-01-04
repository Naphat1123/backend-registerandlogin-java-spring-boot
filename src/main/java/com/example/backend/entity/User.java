package com.example.backend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "m_User")
public class User extends BaseEntity {

    @Column(nullable = false, unique = true, length = 60)
    private String email;

    @Column(nullable = false, length = 120)
    private String password;

    @Column(nullable = false, length = 60)
    private String name;

    @Column(length = 60)
    private String gender;

    @Column(length = 30)
    private String phone_number;

    @Column(length = 60)
    private String dateOfBirth;

    @OneToOne(mappedBy = "user")
    private Social social;

    @OneToMany(mappedBy = "user")
    private List<Product> product;

    @OneToMany(mappedBy = "user")
    private List<Address> addresses;

}
