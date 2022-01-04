package com.example.backend.business;

import com.example.backend.entity.Product;
import com.example.backend.entity.User;
import com.example.backend.exception.BaseException;
import com.example.backend.mapper.ProductMappper;
import com.example.backend.model.ProductDto;
import com.example.backend.model.ProductRequest;
import com.example.backend.model.SearchProductRequest;
import com.example.backend.service.ProductService;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductBusiness {
    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductMappper productMappper;


    public ProductDto createProduct(ProductRequest request) throws BaseException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = (String) authentication.getPrincipal();

        Optional<User> userOptional = userService.findById(userId);
        if (userOptional.isEmpty()) {
            throw new BaseException("can't find user");
        }
        User user = userOptional.get();

        if (ObjectUtils.isEmpty(request.getPrice()) || ObjectUtils.isEmpty(request.getName())) {
            throw new BaseException("price or name can't be empty");
        }

        Product product = productService.createProduct(user, request);

        return productMappper.toProduct(product);

    }


    public ProductDto updateProduct(ProductRequest request) throws BaseException {

        if (ObjectUtils.isEmpty(request.getId())) {
            throw new BaseException("id doesn't empty");
        }

        Optional<Product> productOptional = productService.findById(request.getId());
        if (productOptional.isEmpty()) {
            throw new BaseException("product doesn't exist");
        }

        Product product = productService.updateProduct(request);

        return productMappper.toProduct(product);
    }

    public void deleteProduct(ProductRequest request) throws BaseException {
        try {
            if (ObjectUtils.isEmpty(request.getId())) {
                throw new BaseException("id can't empty");
            }
            productService.deleteProduct(request.getId());
        } catch (BaseException e) {
            throw new BaseException("can't delete product");
        }
    }

    public List<ProductDto> getAllProduct() throws BaseException {
        try {
            List<Product> productList = productService.findAll();

            return productList.stream()
                    .map(product -> productMappper.toProduct(product))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new BaseException("can't find product");
        }
    }

    public List<ProductDto> getSearchProduct(SearchProductRequest request) throws BaseException {
        try {

            List<Product> productList;

            if (ObjectUtils.isEmpty(request.getSearchValue())) {
                productList = productService.findAll();
            }

            productList = productService.getSearchProduct(request.getSearchValue());

            return productList.stream()
                    .map(product -> productMappper.toProduct(product))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new BaseException("can't search product");
        }
    }
}
