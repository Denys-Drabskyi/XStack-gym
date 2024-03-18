package org.example.exception;

import lombok.experimental.StandardException;

@StandardException
public class SuffixUpdateException extends RuntimeException {
  public static SuffixUpdateException wrongUsername(String actualUsername, String received){
    String errorString = String.format(
        "Wrong call to add suffix after %s, for user with username:%s",
        received,
        actualUsername
    );
    return new SuffixUpdateException(errorString);
  }
}
