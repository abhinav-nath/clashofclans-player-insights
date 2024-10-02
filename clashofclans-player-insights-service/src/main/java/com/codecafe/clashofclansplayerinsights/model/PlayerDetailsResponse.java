package com.codecafe.clashofclansplayerinsights.model;

import lombok.Data;

@Data
public class PlayerDetailsResponse {

  private String tag;
  private String name;
  private int townHallLevel;
  private int trophies;

}