package com.codecafe.clashofclansplayerinsights.controller;

import com.codecafe.clashofclansplayerinsights.model.GetPlayerDetailsRequest;
import com.codecafe.clashofclansplayerinsights.model.PlayerDetailsResponse;
import com.codecafe.clashofclansplayerinsights.service.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

  private final PlayerService playerService;

  public PlayerController(PlayerService playerService) {
    this.playerService = playerService;
  }

  @GetMapping("/{playerId}")
  public ResponseEntity<?> getPlayerDetails(@RequestBody GetPlayerDetailsRequest getPlayerDetailsRequest) {
    PlayerDetailsResponse playerDetails = playerService.fetchPlayerDetails(getPlayerDetailsRequest.getPlayerId(), getPlayerDetailsRequest.getApiKey());
    return ResponseEntity.ok(playerDetails);
  }

}