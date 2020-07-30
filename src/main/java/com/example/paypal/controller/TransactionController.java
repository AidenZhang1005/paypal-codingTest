package com.example.paypal.controller;

import com.example.paypal.entity.Transaction;
import com.example.paypal.service.TransactionService;
import com.example.paypal.vo.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
  private static final Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);
  private final TransactionService transactionService;

  /**
   * Constructor
   * @param transactionService transactionService to be injected
   */
  @Autowired
  public TransactionController(TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  /**
   * Get the transactions information for a user
   * @param userId userId
   * @return ResponseEntity<List<Transaction>> List of transactions for this user
   */
  @GetMapping("/user/{userId}")
  public ResponseEntity<List<Transaction>> getTransactionsByUserId(@PathVariable long userId) {
    List<Transaction> transactions = transactionService.getTransactionByUserId(userId);
    LOGGER.info("transactions found for userId = " + userId);
    return new ResponseEntity<>(transactions, HttpStatus.OK);
  }

  /**
   * Get transactions based on particular transaction type for a particular user
   * @param type transaction type
   * @param userId userId
   * @return ResponseEntity<List<Transaction>> List of transactions for this type and for this user
   */
  @GetMapping("/{type}/user/{userId}")
  public ResponseEntity<List<Transaction>> getTransactionByTypeAndUserId(@PathVariable String type, @PathVariable long userId) {
    List<Transaction> transactions = transactionService.getTransactionByTypeAndUserId(type, userId);
    LOGGER.info("transactions found for type = " + type + ", userId = " + userId);
    return new ResponseEntity<>(transactions, HttpStatus.OK);
  }

  /**
   * Gett information on various types of payment transactions done on a specific day by all users
   * @param date date
   * @return ResponseEntity<List<Transaction>> List of transactions on that date
   */
  @GetMapping("/{date}")
  public ResponseEntity<List<Transaction>> getTransactionByDate(@PathVariable("date") String date) {
    List<Transaction> transactions = transactionService.getTransactionByDate(date);
    LOGGER.info("transactions found for date on " + date);
    return new ResponseEntity<>(transactions, HttpStatus.OK);
  }

  /**
   * Handle server exceptions
   * @param ex exception
   * @return ResponseEntity<ErrorResponse> error message with http status code
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
    ErrorResponse error = new ErrorResponse();
    error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    error.setMessage(ex.getMessage());
    LOGGER.error("Controller Error ",ex);
    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
