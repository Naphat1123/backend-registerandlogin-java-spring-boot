package com.example.backend.repository;

import com.example.backend.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TransactionRepo extends JpaRepository<Transaction, String> {

    @Query(value = "select sum(product_price) from Transaction t where t.seller_id = :userId", nativeQuery = true)
    Long getIncome(@Param("userId") String userId);
}
