package com.portfoliodeveloper.controller.developer;

import com.portfoliodeveloper.controller.Resource;
import com.portfoliodeveloper.entity.Developer;
import com.portfoliodeveloper.service.developer.RetrieveLoggedDeveloper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Developer", description = "Developer API")
public class DeveloperRetrieveLoggedController implements Resource {
  private final RetrieveLoggedDeveloper retrieveLoggedDeveloper;

  @GetMapping(value = DEVELOPER)
  @Operation(summary = "Retrieve logged developer")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "SignUp successfully created"),
        @ApiResponse(responseCode = "400", description = "A validation error was thrown"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
      })
  public Developer.DTO execute() {
    return retrieveLoggedDeveloper.execute();
  }
}
