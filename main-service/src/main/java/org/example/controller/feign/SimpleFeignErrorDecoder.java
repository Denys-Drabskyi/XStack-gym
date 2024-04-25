package org.example.controller.feign;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import org.example.exception.ServiceResponseException;

@RequiredArgsConstructor
public class SimpleFeignErrorDecoder implements ErrorDecoder {
  private final String serviceName;

  @Override
  public Exception decode(String methodKey, Response response) {
    throw ServiceResponseException.withResponse(serviceName, response);
  }
}
