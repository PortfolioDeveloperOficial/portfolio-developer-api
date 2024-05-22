package com.portfoliodeveloper;

import com.portfoliodeveloper.entity.Developer;

public class PortfolioDeveloperIntegration {
  public static final String EMAIL = "pd@portfoliodeveloper.com";

  public static Developer.DTO buildDeveloperDTO() {
    return Developer.DTO
        .builder()
        .name("John Doe")
        .email(EMAIL)
        .pdi("098123")
        .phone("11999999999")
        .build();
  }
}
