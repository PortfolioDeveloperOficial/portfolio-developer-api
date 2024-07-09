package com.portfoliodeveloper.controller.auth;

import com.portfoliodeveloper.controller.Resource;
import com.portfoliodeveloper.entity.Developer;
import com.portfoliodeveloper.service.developer.LoginDeveloper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Sign", description = "Authentication API")
public class SignInController implements Resource {
  private final LoginDeveloper loginDeveloper;

  @PostMapping(SIGN_IN)
  @Operation(summary = "Login a new developer")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "SignUp successfully created"),
        @ApiResponse(responseCode = "400", description = "A validation error was thrown"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
      })
  public void execute(@RequestBody final Developer.DTO dto) {
    this.loginDeveloper.execute(dto);
  }
}
