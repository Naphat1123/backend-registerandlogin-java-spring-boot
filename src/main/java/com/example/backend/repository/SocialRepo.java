package com.example.backend.repository;

import com.example.backend.entity.Social;
import com.example.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SocialRepo extends JpaRepository<Social, String> {

    Optional<Social> findByUser(User user);


}
