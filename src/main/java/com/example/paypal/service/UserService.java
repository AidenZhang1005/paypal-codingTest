package com.example.paypal.service;

import com.example.paypal.entity.User;

import java.util.Optional;

public interface UserService {
  Optional<User> getUserById(long userId);
}
