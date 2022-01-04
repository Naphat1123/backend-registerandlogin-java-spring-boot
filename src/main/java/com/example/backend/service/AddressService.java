package com.example.backend.service;

import com.example.backend.entity.Address;
import com.example.backend.repository.AddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepo addressRepo;

    public Optional<Address> findById(String id) {
        return addressRepo.findById(id);
    }

    public Address updateAddress(Address address) {
        return addressRepo.save(address);
    }

    public boolean existById(String id) {
        return addressRepo.existsById(id);
    }

    public void deleteAddress(String id) {
        addressRepo.deleteById(id);
    }
}
