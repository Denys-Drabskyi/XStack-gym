package org.example.exception;

import lombok.experimental.StandardException;

@StandardException
public class EntityNotFoundException extends RuntimeException {
  public static <T> EntityNotFoundException byId(T id, String entityName){
    return new EntityNotFoundException(String.format("%s with id: %s does not exist", entityName, id));
  }

  public static <T> EntityNotFoundException byUsername(T username, String entityName){
    return new EntityNotFoundException(String.format("%s with Username: %s does not exist", entityName, username));
  }
}
