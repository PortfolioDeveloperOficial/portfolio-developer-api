package com.portfoliodeveloper.controller.auth;

import com.portfoliodeveloper.entity.Developer;
import com.portfoliodeveloper.service.developer.CreateDeveloperService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SignUpController {
  private final CreateDeveloperService createDeveloperService;

  @PostMapping("/sign-up")
  public void execute(@RequestBody final Developer.DTO dto) {
    createDeveloperService.execute(dto);
  }
}
