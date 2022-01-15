package com.example.backend.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity(name = "category")
public class Category {

    @Id
    @Column(length = 36, nullable = false, updatable = false)
    private String code;

    @Column(nullable = false, length = 60)
    private String name;

    @OneToMany(mappedBy = "category")
    @ToString.Exclude
    private List<Product> products;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Category category = (Category) o;
        return code != null && Objects.equals(code, category.code);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
