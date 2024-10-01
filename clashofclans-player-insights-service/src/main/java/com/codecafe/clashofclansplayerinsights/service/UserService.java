package com.codecafe.clashofclansplayerinsights.service;

import com.codecafe.clashofclansplayerinsights.entity.User;
import com.codecafe.clashofclansplayerinsights.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public User registerUser(String username, String password, String apiKey) {
    User user = User.builder()
                    .username(username)
                    .password(passwordEncoder.encode(password))
                    .apiKey(apiKey)
                    .build();
    return userRepository.save(user);
  }

  public User findByUsername(String username) {
    return userRepository.findById(username)
                         .orElseThrow(() -> new RuntimeException("User with ID " + username + " not found"));
  }

}