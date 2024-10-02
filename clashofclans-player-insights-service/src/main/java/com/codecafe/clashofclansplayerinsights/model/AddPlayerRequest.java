package com.codecafe.clashofclansplayerinsights.model;

import lombok.Data;

@Data
public class AddPlayerRequest {

  private String playerId;
  private String userId;

}