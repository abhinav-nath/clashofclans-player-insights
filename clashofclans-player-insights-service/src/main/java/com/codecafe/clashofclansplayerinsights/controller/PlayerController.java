package com.codecafe.clashofclansplayerinsights.controller;

import com.codecafe.clashofclansplayerinsights.entity.Player;
import com.codecafe.clashofclansplayerinsights.entity.User;
import com.codecafe.clashofclansplayerinsights.service.PlayerService;
import com.codecafe.clashofclansplayerinsights.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

  @Autowired
  private PlayerService playerService;

  @Autowired
  private UserService userService;

  @PostMapping("/add")
  public Player addPlayer(@RequestParam String playerId, @RequestParam String apiKey, @RequestParam Long userId) {
    // Here you could add logic to fetch player details via CoC API using the apiKey
    User user = userService.findByUsername("username").orElseThrow();
    Player player = Player.builder()
                          .playerId(playerId)
                          .user(user)
                          .build();
    return playerService.savePlayer(player);
  }

  @GetMapping("/user/{userId}")
  public List<Player> getPlayers(@PathVariable Long userId) {
    return playerService.getPlayersByUserId(userId);
  }

}