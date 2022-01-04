package com.example.backend.api;

import com.example.backend.business.ProductBusiness;
import com.example.backend.business.UserBusiness;
import com.example.backend.exception.BaseException;
import com.example.backend.model.ProductDto;
import com.example.backend.model.ProductRequest;
import com.example.backend.model.SearchProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductApi {

    @Autowired
    private UserBusiness userBusiness;
    @Autowired
    private ProductBusiness productBusiness;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProduct() throws BaseException {
        return ResponseEntity.ok(productBusiness.getAllProduct());
    }

    @PostMapping("/search")
    public ResponseEntity<List<ProductDto>> searchProduct(@RequestBody SearchProductRequest request) throws BaseException {
        return ResponseEntity.ok(productBusiness.getSearchProduct(request));
    }

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

    @DeleteMapping
    public ResponseEntity<String> deleteProduct(@RequestBody ProductRequest request) throws BaseException {
        productBusiness.deleteProduct(request);
        return ResponseEntity.ok("deleted");
    }
}
