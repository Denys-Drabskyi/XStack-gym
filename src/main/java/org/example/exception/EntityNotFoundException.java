package org.example.exception;

import java.util.List;
import lombok.experimental.StandardException;

@StandardException
public class EntityNotFoundException extends RuntimeException {

  public static <T> EntityNotFoundException byUsername(T username, String entityName){
    return new EntityNotFoundException(String.format("%s with Username: %s does not exist", entityName, username));
  }

  public static EntityNotFoundException types(List<String> types) {
    StringBuilder message = new StringBuilder();
    message.append("Some training types was not found:\n");
    types.forEach(type -> message.append(String.format("Type with name:'%s' not found %n", type)));
    return new EntityNotFoundException(message.toString());
  }
}
