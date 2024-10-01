package com.codecafe.clashofclansplayerinsights.service;

import com.codecafe.clashofclansplayerinsights.entity.Player;
import com.codecafe.clashofclansplayerinsights.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

  @Autowired
  private PlayerRepository playerRepository;

  public List<Player> getPlayersByUserId(Long userId) {
    return playerRepository.findByUserId(userId);
  }

  public Player savePlayer(Player player) {
    return playerRepository.save(player);
  }

}