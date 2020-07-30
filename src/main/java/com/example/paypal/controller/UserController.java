package com.example.paypal.controller;

import com.example.paypal.entity.User;
import com.example.paypal.exception.UserNotFoundException;
import com.example.paypal.service.UserService;
import com.example.paypal.vo.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/users")
public class UserController {
  private static final Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);
  private final UserService userService;

  /**
   * Constructor
   * @param userService userService to be injected
   */
  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  /**
   * Get the User information by userId
   * @param userId userId
   * @return ResponseEntity<User> user with http status code
   */
  @GetMapping("/{userId}")
  public ResponseEntity<User> getUserById(@PathVariable long userId) {
    Optional<User> user = userService.getUserById(userId);
    if (!user.isPresent()) {
      throw new UserNotFoundException("user with id = " + userId + " not found");
    }
    LOGGER.info("user with id = " + userId + " found.");
    return new ResponseEntity<>(user.get(), HttpStatus.OK);
  }

  /**
   * Handle UserNotFoundException
   * @param ex exception
   * @return ResponseEntity<ErrorResponse> error message with http status code
   */
  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<ErrorResponse> exceptionHandlerPersonNotFound(Exception ex) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setMessage(ex.getMessage());
    errorResponse.setErrorCode(HttpStatus.NOT_FOUND.value());
    LOGGER.error(errorResponse.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
    ErrorResponse error = new ErrorResponse();
    error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    error.setMessage(ex.getMessage());
    LOGGER.error("Controller Error ",ex);
    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
