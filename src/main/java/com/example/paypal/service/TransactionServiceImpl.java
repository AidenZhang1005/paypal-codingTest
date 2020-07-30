package com.example.paypal.service;

import com.example.paypal.dao.TransactionRepository;
import com.example.paypal.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {
  private final TransactionRepository transactionRepository;

  /**
   * Constructor
   * @param transactionRepository transactionRepository to be injected
   */
  @Autowired
  public TransactionServiceImpl(TransactionRepository transactionRepository) {
    this.transactionRepository = transactionRepository;
  }

  /**
   * Get the transactions information for a user
   * @param userId userId
   * @return List<Transaction> list of transaction meet the requirement
   */
  @Override
  public List<Transaction> getTransactionByUserId(long userId) {
    return transactionRepository.getTransactionByUserId(userId);
  }

  /**
   * Get transactions based on particular transaction type for a particular user
   * @param type type
   * @param userId userId
   * @return List<Transaction> list of transaction meet the requirement
   */
  @Override
  public List<Transaction> getTransactionByTypeAndUserId(String type, long userId) {
    return transactionRepository.getTransactionByTypeAndUserId(type, userId);
  }

  /**
   * Get the transactions on a date
   * @param date date
   * @return List<Transaction> list of transactions on that date
   */
  @Override
  public List<Transaction> getTransactionByDate(String date) {
    List<Transaction> transactions = transactionRepository.findAll();
    return transactions
            .stream()
            .filter(t -> t.getTransactOn().toString().contains(date))
            .collect(Collectors.toList());
  }
}
