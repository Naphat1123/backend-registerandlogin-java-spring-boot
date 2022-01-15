package com.example.backend.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity(name = "m_social")
public class Social extends BaseEntity {

    @Column(nullable = false, unique = true, length = 60)
    private String facebook;

    @Column(nullable = false, length = 120)
    private String line;

    @Column(nullable = false, length = 60)
    private String instagram;

    @Column(nullable = false, length = 60)
    private String tiktok;

    @OneToOne
    @JoinColumn(name = "m_user_id")
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Social social = (Social) o;
        return getId() != null && Objects.equals(getId(), social.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
