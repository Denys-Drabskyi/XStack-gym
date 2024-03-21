package org.example.exception;

import java.util.List;
import lombok.experimental.StandardException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@StandardException
public class EntityNotFoundException extends RuntimeException {

  public static <T> EntityNotFoundException byUsername(T username, String entityName) {
    log.info("{} with username:{} not found", entityName, username);
    return new EntityNotFoundException(String.format("%s with Username: %s does not exist", entityName, username));
  }

  public static EntityNotFoundException types(List<String> types) {
    StringBuilder message = new StringBuilder();
    message.append("Some training types was not found:\040");
    types.forEach(type -> message.append(String.format("Type with name:'%s' not found %n\040", type)));
    return new EntityNotFoundException(message.toString());
  }
}
