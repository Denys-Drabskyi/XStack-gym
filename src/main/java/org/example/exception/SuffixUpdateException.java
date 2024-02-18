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

  public static SuffixUpdateException duringFiltering(String username, Exception e) {
    String errorString = String.format("Exception during users with username:%s finding process", username);
    return new SuffixUpdateException(errorString, e);
  }
}
