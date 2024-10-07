package com.codecafe.clashofclansplayerinsights.model;

import lombok.Data;

@Data
public class GetPlayerDetailsRequest {

  private String playerId;
  private String apiKey;

}