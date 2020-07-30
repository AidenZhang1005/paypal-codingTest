package com.example.paypal.dao;

import com.example.paypal.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
  @Query("SELECT transaction " +
         "FROM Transaction transaction " +
         "JOIN User user " +
         "ON transaction.user.userId = user.userId " +
         "WHERE user.userId = :userId")
  List<Transaction> getTransactionByUserId(@Param("userId") long userId);

  @Query("SELECT transaction " +
         "FROM Transaction transaction " +
         "JOIN User user " +
         "ON transaction.user.userId = user.userId " +
         "WHERE transaction.type = :type AND user.userId = :userId")
  List<Transaction> getTransactionByTypeAndUserId(@Param("type") String type, @Param("userId") long userId);
}
