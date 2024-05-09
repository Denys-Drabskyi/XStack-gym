package org.example.exception;

import feign.Response;
import lombok.experimental.StandardException;
import org.springframework.http.HttpStatus;

@StandardException
public class ServiceResponseException extends RuntimeException {
  public static ServiceResponseException withResponse(String serviceName, Response response) {
    String message =
        String.format("Failed call service: %s; response: %s", serviceName, HttpStatus.valueOf(response.status()));
    return new ServiceResponseException(message);
  }
}
