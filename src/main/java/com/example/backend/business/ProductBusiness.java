package com.example.backend.business;

import com.example.backend.entity.Category;
import com.example.backend.entity.Product;
import com.example.backend.entity.User;
import com.example.backend.exception.BaseException;
import com.example.backend.mapper.ProductMappper;
import com.example.backend.model.ListProductDto;
import com.example.backend.model.ProductDto;
import com.example.backend.model.ProductRequest;
import com.example.backend.model.SearchProductRequest;
import com.example.backend.service.CategoryService;
import com.example.backend.service.ProductService;
import com.example.backend.service.TransactionService;
import com.example.backend.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
public class ProductBusiness {
    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductMappper productMappper;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TransactionService transactionService;


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

        Optional<Category> categoryOptional = categoryService.findByCode(request.getCatagory_code());
        Category category = categoryOptional.get();
        log.info("category = {}", category);

        Product product = productService.createProduct(user, request, category);

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

    public ListProductDto getSearchProduct(SearchProductRequest request) throws BaseException {
        try {

            ListProductDto listProductDto = new ListProductDto();

            int page_number = request.getPage_number();
            int page_size = request.getPage_size();

            if (ObjectUtils.isEmpty(request.getPage_number())) {
                page_number = 0;
            }

            if (ObjectUtils.isEmpty(request.getPage_size())) {
                page_size = 0;
            }

            Pageable pageable = PageRequest.of(page_number, page_size);

            Page<Product> productList;

            if (ObjectUtils.isEmpty(request.getSearchValue())) {
                productList = productService.findAllProduct(pageable);
            }

            productList = productService.getSearchProduct(request.getSearchValue(), pageable);

            List<Product> collectProduct = productList.get().collect(Collectors.toList());

            List<ProductDto> collect = collectProduct.stream()
                    .map(product -> productMappper.toProduct(product))
                    .collect(Collectors.toList());

            listProductDto.setCount((int) productList.getTotalElements());
            listProductDto.setProductList(collect);

            return listProductDto;

        } catch (Exception e) {
            throw new BaseException("can't search product");
        }
    }

    public void buyProduct(List<ProductRequest> request) throws BaseException {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String userId = (String) authentication.getPrincipal();


        if (ObjectUtils.isEmpty(request)) {
            throw new BaseException("request is empty");
        }

        Optional<User> buyerOpt = userService.findById(userId);
        User buyer = buyerOpt.get();

        for (ProductRequest p : request) {

            Optional<Product> productOptional = productService.findById(p.getId());
            Product product = productOptional.get();

            transactionService.create(product, buyer);

        }

    }

}
