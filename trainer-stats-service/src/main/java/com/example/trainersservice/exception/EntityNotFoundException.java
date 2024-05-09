package com.example.trainersservice.exception;

import lombok.experimental.StandardException;

@StandardException
public class EntityNotFoundException extends RuntimeException {
  public static EntityNotFoundException year(int year, String username) {
    return new EntityNotFoundException(String.format("Year %s is not found for username:%s", year, username));
  }

  public static EntityNotFoundException month(int month, String username) {
    return new EntityNotFoundException(String.format("Month %s is not found for username:%s", month, username));
  }

  public static EntityNotFoundException summary(String username) {
    return new EntityNotFoundException(String.format("Summary for trainer:%s not found", username));
  }
}
