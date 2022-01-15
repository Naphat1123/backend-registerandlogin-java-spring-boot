package com.example.backend.api;

import com.example.backend.business.TransactionBussiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionApi {

    @Autowired
    private TransactionBussiness transactionBussiness;

    @GetMapping("/total-income")
    public ResponseEntity<String> getIncome() {
        Long result = transactionBussiness.getIncome();
        return ResponseEntity.ok("total income = " + result);
    }

}
