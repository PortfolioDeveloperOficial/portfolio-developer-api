package com.portfoliodeveloper.configuration;

import br.com.senioritymeter.security.gateway.SMUserDetails;
import com.portfoliodeveloper.exception.BadRequestException;
import com.portfoliodeveloper.repository.DeveloperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SMUserDetailsImpl implements SMUserDetails {
  private final DeveloperRepository developerRepository;

  @Override
  public UserDetails loadUserDetails(String username) {
    var developer =
        this.developerRepository
            .findByEmail(username)
            .orElseThrow(BadRequestException::developerNotFound);

    return UserDetailsImpl.of(developer);
  }
}
