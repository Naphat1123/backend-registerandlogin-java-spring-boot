package com.example.backend.api;

import com.example.backend.business.UserBusiness;
import com.example.backend.exception.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductApi {

    @Autowired
    private UserBusiness userBusiness;

    @GetMapping("/{id}")
    public ResponseEntity<String> getProductId(@PathVariable("id") String id) throws BaseException {
        String response = userBusiness.getProductId(id);
        return ResponseEntity.ok(response);
    }
}
