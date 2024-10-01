package com.codecafe.clashofclansplayerinsights.controller;

import com.codecafe.clashofclansplayerinsights.entity.Player;
import com.codecafe.clashofclansplayerinsights.entity.User;
import com.codecafe.clashofclansplayerinsights.service.PlayerService;
import com.codecafe.clashofclansplayerinsights.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private UserService userService;

  @Autowired
  private PlayerService playerService;

  @PostMapping
  public User createUser(@Valid @RequestBody User user) {
    return userService.registerUser(user.getUsername(), user.getPassword(), user.getApiKey());
  }

  @PutMapping("/{userId}/players")
  public Player addOrUpdatePlayerToUser(@PathVariable String userName, @RequestParam String playerId) {

    User user = userService.findByUsername(userName);

    // Fetch player details from CoC API (logic omitted for simplicity)
    Player player = Player.builder()
                          .playerId(playerId)
                          .user(user)
                          .build();

    // Save the player and associate it with the user (idempotent update)
    return playerService.savePlayer(player);
  }

}