package com.codecafe.clashofclansplayerinsights.service;

import com.codecafe.clashofclansplayerinsights.entity.User;
import com.codecafe.clashofclansplayerinsights.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public User registerUser(String username, String password, String apiKey) {
    if (userRepository.existsById(username)) {
      throw new RuntimeException("Username " + username + " is already taken");
    }

    // Hash the password before saving
    String encodedPassword = passwordEncoder.encode(password);

    User newUser = User.builder()
                       .username(username)
                       .password(encodedPassword)
                       .apiKey(apiKey)
                       .playerIds(new HashSet<>())
                       .build();

    // Save the user in the database
    return userRepository.save(newUser);
  }

  public User getUserByUsername(String username) {
    return userRepository.findById(username)
                         .orElseThrow(() -> new RuntimeException("User with username " + username + " not found"));
  }

  public User saveUser(User user) {
    return userRepository.save(user);
  }

}