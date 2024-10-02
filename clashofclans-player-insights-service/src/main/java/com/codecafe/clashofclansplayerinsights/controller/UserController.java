package com.codecafe.clashofclansplayerinsights.controller;

import com.codecafe.clashofclansplayerinsights.entity.User;
import com.codecafe.clashofclansplayerinsights.model.CreateUserRequest;
import com.codecafe.clashofclansplayerinsights.model.PlayerDetailsResponse;
import com.codecafe.clashofclansplayerinsights.service.PlayerService;
import com.codecafe.clashofclansplayerinsights.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static java.util.concurrent.CompletableFuture.supplyAsync;
import static org.springframework.util.CollectionUtils.isEmpty;

@RestController
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;
  private final PlayerService playerService;

  public UserController(UserService userService, PlayerService playerService) {
    this.userService = userService;
    this.playerService = playerService;
  }

  @PostMapping
  public User createUser(@Valid @RequestBody CreateUserRequest user) {
    return userService.registerUser(user.getUsername(), user.getPassword(), user.getApiKey());
  }

  @PutMapping("/{username}/players")
  public User addPlayerIds(@PathVariable String username, @RequestBody Set<String> playerIds) {
    User user = userService.getUserByUsername(username);

    if (!isEmpty(user.getPlayerIds())) {
      user.getPlayerIds().removeAll(playerIds);
    }

    user.getPlayerIds().addAll(playerIds);

    return userService.saveUser(user);
  }

  @GetMapping
  public List<PlayerDetailsResponse> getAllPlayerDetailsForUser(@PathVariable String username) {
    User user = userService.getUserByUsername(username);
    Set<String> playerIds = user.getPlayerIds();
    String apiKey = user.getApiKey();

    List<CompletableFuture<PlayerDetailsResponse>> futures =
      playerIds.stream()
               .map(playerId -> supplyAsync(() -> playerService.fetchPlayerDetails(playerId, apiKey)))
               .toList();

    return futures.stream()
                  .map(CompletableFuture::join)
                  .collect(Collectors.toList());
  }

}