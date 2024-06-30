package com.portfoliodeveloper.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.portfoliodeveloper.enumeration.Gender;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "developers")
public class Developer {
  @Id private UUID id;
  private String pdi;
  private String name;
  private String email;
  @Setter private String code;
  private String phones;
  private LocalDate birthDate;

  @Enumerated(EnumType.STRING)
  private Gender gender;

  private String photo;
  private String bio;
  private String street;
  private String number;
  private String neighborhood;
  private String cep;
  private String city;
  private String state;
  private String country;
  private String github;
  private String linkedin;

  public static Developer create(final DTO dto) {
    return Developer.builder()
        .id(UUID.randomUUID())
        .pdi(dto.getPdi())
        .name(dto.getName())
        .email(dto.getEmail())
        .code(dto.getCode())
        .phones(dto.getPhone())
        .birthDate(dto.getBirthDate())
        .gender(dto.getGender())
        .photo(dto.getImageUrl())
        .bio(dto.getBio())
        .street(dto.getStreet())
        .number(dto.getNumber())
        .neighborhood(dto.getNeighborhood())
        .cep(dto.getCep())
        .city(dto.getCity())
        .state(dto.getState())
        .country(dto.getCountry())
        .linkedin(dto.getLinkedin())
        .github(dto.getGithub())
        .build();
  }

  public DTO toDTO() {
    return DTO.builder()
        .id(id)
        .pdi(pdi)
        .name(name)
        .email(email)
        .phone(phones)
        .birthDate(birthDate)
        .gender(gender)
        .imageUrl(photo)
        .bio(bio)
        .street(street)
        .number(number)
        .neighborhood(neighborhood)
        .cep(cep)
        .city(city)
        .state(state)
        .country(country)
        .linkedin(linkedin)
        .github(github)
        .build();
  }

  @Data
  @Builder
  @JsonInclude(JsonInclude.Include.NON_NULL)
  public static class DTO {
    private UUID id;
    private String pdi;
    private String name;
    private String email;
    private String code;
    private String phone;
    private LocalDate birthDate;
    private Gender gender;
    private String imageUrl;
    private String bio;
    private String street;
    private String number;
    private String neighborhood;
    private String cep;
    private String city;
    private String state;
    private String country;
    private String linkedin;
    private String github;

    public static DTO of(String email) {
      return DTO.builder().email(email).build();
    }
  }
}
