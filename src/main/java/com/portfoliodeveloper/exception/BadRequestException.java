package com.portfoliodeveloper.exception;

public class BadRequestException extends RuntimeException {
  public static final String DEVELOPER_ALREADY_EXISTS = "Developer already exists";
  public static final String DEVELOPER_NOT_EXISTS = "Developer does not exists";

  private BadRequestException(final String message) {
    super(message);
  }

  public static BadRequestException developerAlreadyExists() {
    return new BadRequestException(DEVELOPER_ALREADY_EXISTS);
  }

  public static BadRequestException developerNotFound() {
    return new BadRequestException(DEVELOPER_NOT_EXISTS);
  }
}
