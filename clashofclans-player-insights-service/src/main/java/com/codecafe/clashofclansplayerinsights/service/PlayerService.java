package com.codecafe.clashofclansplayerinsights.service;

import com.codecafe.clashofclansplayerinsights.model.PlayerDetailsResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;

@Service
public class PlayerService {

  private static final String COC_API_URL = "https://api.clashofclans.com/v1/players/";

  private final RestTemplate restTemplate;

  public PlayerService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public PlayerDetailsResponse fetchPlayerDetails(String playerId, String apiKey) {
    String url = COC_API_URL + URLEncoder.encode("#") + playerId;

    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", apiKey);

    HttpEntity<String> entity = new HttpEntity<>(headers);

    try {
      ResponseEntity<PlayerDetailsResponse> response = restTemplate.exchange(
        url, HttpMethod.GET, entity, PlayerDetailsResponse.class
      );
      return response.getBody();
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    return null;

  }

}