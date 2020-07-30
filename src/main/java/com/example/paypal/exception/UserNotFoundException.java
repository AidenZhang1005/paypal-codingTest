package com.example.paypal.exception;

public class UserNotFoundException extends RuntimeException {
  private String errorMessage;

  public String getErrorMessage() {
    return errorMessage;
  }

  public UserNotFoundException(String errorMessage) {
    super(errorMessage);
    this.errorMessage = errorMessage;
  }

  public UserNotFoundException() {
    super();
  }
}
