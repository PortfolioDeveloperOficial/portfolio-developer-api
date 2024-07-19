package com.portfoliodeveloper.service.developer;

import com.portfoliodeveloper.entity.Developer;
import com.portfoliodeveloper.exception.BadRequestException;
import com.portfoliodeveloper.repository.DeveloperRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateDeveloper {
  private final DeveloperRepository developerRepository;

  public void execute(final Developer.DTO dto) {
    var optional = this.developerRepository.findById(dto.getId());
    if (optional.isEmpty()) {
      throw BadRequestException.developerNotFound();
    }

    var developer = optional.get().toDTO();

    developer.setCode(developer.getCode());
    developer.setPdi(Optional.ofNullable(dto.getPdi()).orElse(developer.getPdi()));
    developer.setName(Optional.ofNullable(dto.getName()).orElse(developer.getName()));
    developer.setEmail(Optional.ofNullable(dto.getEmail()).orElse(developer.getEmail()));
    developer.setPhone(Optional.ofNullable(dto.getPhone()).orElse(developer.getPhone()));
    developer.setBirthDate(
        Optional.ofNullable(dto.getBirthDate()).orElse(developer.getBirthDate()));
    developer.setGender(Optional.ofNullable(dto.getGender()).orElse(developer.getGender()));
    developer.setImageUrl(Optional.ofNullable(dto.getImageUrl()).orElse(developer.getImageUrl()));
    developer.setBio(Optional.ofNullable(dto.getBio()).orElse(developer.getBio()));
    developer.setStreet(Optional.ofNullable(dto.getStreet()).orElse(developer.getStreet()));
    developer.setNumber(Optional.ofNullable(dto.getNumber()).orElse(developer.getNumber()));
    developer.setNeighborhood(
        Optional.ofNullable(dto.getNeighborhood()).orElse(developer.getNeighborhood()));
    developer.setCep(Optional.ofNullable(dto.getCep()).orElse(developer.getCep()));
    developer.setCity(Optional.ofNullable(dto.getCity()).orElse(developer.getCity()));
    developer.setState(Optional.ofNullable(dto.getState()).orElse(developer.getState()));
    developer.setCountry(Optional.ofNullable(dto.getCountry()).orElse(developer.getCountry()));
    developer.setLinkedin(Optional.ofNullable(dto.getLinkedin()).orElse(developer.getLinkedin()));
    developer.setGithub(Optional.ofNullable(dto.getGithub()).orElse(developer.getGithub()));

    this.developerRepository.save(developer.toEntity());
  }
}
