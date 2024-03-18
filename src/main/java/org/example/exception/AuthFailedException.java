package org.example.exception;

import lombok.experimental.StandardException;
import org.example.dto.UserCredentialsDto;

@StandardException
public class AuthFailedException extends RuntimeException {
  public static AuthFailedException forUser(UserCredentialsDto dto) {
    String message = String.format("Failed auth for user:%s", dto.getUsername());
    return new AuthFailedException(message);
  }
}
