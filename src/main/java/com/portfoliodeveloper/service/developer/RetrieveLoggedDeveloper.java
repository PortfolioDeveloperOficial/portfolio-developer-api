package com.portfoliodeveloper.service.developer;

import br.com.senioritymeter.security.interaction.LoggedUser;
import com.portfoliodeveloper.entity.Developer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RetrieveLoggedDeveloper {
  private final RetrieveDeveloper retrieveDeveloper;

  public Developer.DTO execute() {
    final var loggedDeveloperEmail = LoggedUser.getUsername();
    final var dto = Developer.DTO.of(loggedDeveloperEmail);
    return retrieveDeveloper.execute(dto).toDTO();
  }
}
