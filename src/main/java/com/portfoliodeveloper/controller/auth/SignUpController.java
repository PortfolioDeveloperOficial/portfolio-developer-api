package com.portfoliodeveloper.controller.auth;

import com.portfoliodeveloper.controller.Resource;
import com.portfoliodeveloper.entity.Developer;
import com.portfoliodeveloper.service.developer.CreateDeveloperService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Sign", description = "Authentication API")
public class SignUpController implements Resource {
  private final CreateDeveloperService createDeveloperService;

  @PostMapping(SIGN_UP)
  @Operation(summary = "Register a new developer")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "201", description = "SignUp successfully created"),
        @ApiResponse(responseCode = "400", description = "A validation error was thrown"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
      })
  @ResponseStatus(HttpStatus.CREATED)
  public void execute(@RequestBody final Developer.DTO dto) {
    createDeveloperService.execute(dto);
  }
}
