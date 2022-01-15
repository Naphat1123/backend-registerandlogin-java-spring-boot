package com.example.backend.business;

import com.example.backend.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class TransactionBussiness {

    @Autowired
    private TransactionService transactionService;


    public Long getIncome() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String userId = (String) authentication.getPrincipal();

        return transactionService.getIncome(userId);

    }

}
