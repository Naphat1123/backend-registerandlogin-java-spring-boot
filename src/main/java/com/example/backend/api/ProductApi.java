package com.example.backend.api;

import com.example.backend.business.ProductBusiness;
import com.example.backend.business.UserBusiness;
import com.example.backend.exception.BaseException;
import com.example.backend.model.ProductDto;
import com.example.backend.model.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/products")
public class ProductApi {

    @Autowired
    private UserBusiness userBusiness;
    @Autowired
    private ProductBusiness productBusiness;

    @GetMapping("/{id}")
    public ResponseEntity<String> getProductId(@PathVariable("id") String id) throws BaseException {
        String response = userBusiness.getProductId(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductRequest request) throws BaseException {
        return ResponseEntity.ok(productBusiness.createProduct(request));
    }

    @PutMapping
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductRequest request) throws BaseException {
        return ResponseEntity.ok(productBusiness.updateProduct(request));
    }
}
