package com.example.backend.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
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
    @ToString.Exclude
    private List<Product> product;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Address> addresses;

    @OneToMany(mappedBy = "seller")
    private List<Transaction> sellerList;

    @OneToMany(mappedBy = "buyer")
    private List<Transaction> buyerList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return getId() != null && Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
