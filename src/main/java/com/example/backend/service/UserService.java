package com.example.backend.service;

import com.example.backend.entity.Address;
import com.example.backend.entity.User;
import com.example.backend.exception.BaseException;
import com.example.backend.model.AddressRequest;
import com.example.backend.repository.AddressRepo;
import com.example.backend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AddressRepo addressRepo;


    public User create(String email, String password, String name) throws BaseException {

        if (Objects.isNull(email)) {
            throw new BaseException("email is empty");
        }

        if (Objects.isNull(name)) {
            throw new BaseException("name is empty");
        }

        if (userRepo.existsByEmail(email)) {
            throw new BaseException("email is exist");
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        userRepo.save(user);
        return user;
    }

    public Optional<User> findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public boolean matchPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public List<User> getAllUser() {
        return userRepo.findAll();
    }

    public User updateUserName(User user) {
        user.setName(user.getName());
        user.setGender(user.getGender());
        user.setPhone_number(user.getPhone_number());
        user.setDateOfBirth(user.getDateOfBirth());
        userRepo.save(user);
        return user;
    }

    public Optional<User> findById(String userId) {
        return userRepo.findById(userId);

    }

    public Address createAddress(User user, AddressRequest request) {
        Address entity = new Address();
        entity.setUser(user);
        entity.setAddress(request.getAddress());
        entity.setCity(request.getCity());
        entity.setZipcode(request.getZipcode());
        return addressRepo.save(entity);
    }
}
