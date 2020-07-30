package com.example.paypal.service;

import com.example.paypal.dao.UserRepository;
import com.example.paypal.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  /**
   * Constructor
   * @param userRepository userRepository to be injected
   */
  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  /**
   * Get user's information based on id
   * @param userId userId
   * @return User
   */
  @Override
  public Optional<User> getUserById(long userId) {
    return userRepository.findById(userId);
  }
}
