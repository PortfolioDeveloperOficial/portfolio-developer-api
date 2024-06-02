package com.portfoliodeveloper.service.developer;

import br.com.senioritymeter.security.interaction.AuthenticateUser;
import br.com.senioritymeter.security.interaction.GenerateToken;
import br.com.senioritymeter.security.valueobject.Token;
import com.portfoliodeveloper.entity.Developer;
import com.portfoliodeveloper.exception.BadRequestException;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConfirmDeveloperService {
  private final AuthenticateUser authenticateUser;
  private final GenerateToken generateToken;
  private final RetrieveDeveloperService retrieveDeveloperService;

  public Token execute(final Developer.DTO dto) {
    var developer = this.retrieveDeveloperService.execute(dto);

    this.authenticate(Developer.create(dto));

    return this.generateToken(developer);
  }

  private void authenticate(Developer developer) {
    try {
      authenticateUser.authenticate(developer.getEmail(), developer.getCode());
    } catch (Exception e) {
      throw BadRequestException.badCredentials();
    }
  }

  private Token generateToken(Developer developer) {
    final var tokenInput =
        GenerateToken.Input.builder()
            .subject(developer.getEmail())
            .expiresAt(Instant.now().plusSeconds(3600))
            .build();

    return generateToken.execute(tokenInput);
  }
}
