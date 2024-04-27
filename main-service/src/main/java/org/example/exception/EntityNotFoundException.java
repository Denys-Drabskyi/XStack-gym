package org.example.exception;

import java.util.UUID;
import lombok.experimental.StandardException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@StandardException
public class EntityNotFoundException extends RuntimeException {

  public static <T> EntityNotFoundException byUsername(T username, String entityName) {
    log.info("{} with username:{} not found", entityName, username);
    return new EntityNotFoundException(String.format("%s with Username: %s does not exist", entityName, username));
  }

  public static <T> EntityNotFoundException byId(T username, UUID id) {
    log.info("{} with id:{} not found", id, username);
    return new EntityNotFoundException(String.format("%s with id: %s does not exist", id, username));
  }

  public static EntityNotFoundException type(String type) {
    return new EntityNotFoundException(String.format("Training type with name:'%s' not found", type));
  }
}
