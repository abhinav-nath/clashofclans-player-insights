package com.codecafe.clashofclansplayerinsights.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CreateUserRequest {

  @NotEmpty(message = "Username is required")
  private String username;

  @NotEmpty(message = "Password is required")
  private String password;

  @NotEmpty(message = "API key is required")
  private String apiKey;

}
