package com.example.backend.service;

import com.example.backend.entity.Product;
import com.example.backend.entity.Transaction;
import com.example.backend.entity.User;
import com.example.backend.exception.BaseException;
import com.example.backend.repository.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepo transactionRepo;

    public Transaction create(Product product, User buyer) throws BaseException {
        try {
            Transaction entity = new Transaction();
            entity.setSeller(product.getUser());
            entity.setProduct_name(product.getName());
            entity.setProduct_price(product.getPrice());
            entity.setBuyer(buyer);
            return transactionRepo.save(entity);
        } catch (Exception e) {
            throw new BaseException("can't save transaction");
        }
    }

    public Long getIncome(String userId) {
        return transactionRepo.getIncome(userId);
    }
}
