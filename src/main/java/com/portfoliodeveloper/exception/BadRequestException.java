package com.portfoliodeveloper.exception;

public class BadRequestException extends RuntimeException {
  public static final String DEVELOPER_ALREADY_EXISTS = "Developer already exists";

  private BadRequestException(final String message) {
    super(message);
  }

  public static BadRequestException developerAlreadyExists() {
    return new BadRequestException(DEVELOPER_ALREADY_EXISTS);
  }
}
