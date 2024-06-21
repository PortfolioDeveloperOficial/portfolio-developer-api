package com.portfoliodeveloper.controller;

import com.portfoliodeveloper.exception.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(value = BadRequestException.class)
  public ResponseEntity<?> badRequestException(final BadRequestException e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error(e.getMessage()));
  }
}
