package com.example.paypal.service;

import com.example.paypal.entity.Transaction;
import java.util.List;

public interface TransactionService {
  List<Transaction> getTransactionByUserId(long userId);
  List<Transaction> getTransactionByTypeAndUserId(String type, long userId);
  List<Transaction> getTransactionByDate(String date);
}
