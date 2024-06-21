package com.portfoliodeveloper.exception;

public class BadRequestException extends RuntimeException {
  public static final String DEVELOPER_ALREADY_EXISTS = "Desenvolvedor já existe com este e-mail";
  public static final String DEVELOPER_NOT_EXISTS = "Desenvolvedor não existe com este e-mail";
  public static final String BAD_CREDENTIALS = "Código não confere";

  private BadRequestException(final String message) {
    super(message);
  }

  public static BadRequestException developerAlreadyExists() {
    return new BadRequestException(DEVELOPER_ALREADY_EXISTS);
  }

  public static BadRequestException developerNotFound() {
    return new BadRequestException(DEVELOPER_NOT_EXISTS);
  }

  public static BadRequestException badCredentials() {
    return new BadRequestException(BAD_CREDENTIALS);
  }
}
