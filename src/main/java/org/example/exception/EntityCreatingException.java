package org.example.exception;

import java.util.UUID;
import lombok.experimental.StandardException;

@StandardException
public class EntityCreatingException extends RuntimeException {
  public static EntityCreatingException alreadyExists(UUID id, String entityName) {
    String errorMessage = String.format("Cannot create %s with id:%s, it is present already", entityName, id);
    return new EntityCreatingException(errorMessage);
  }
}
