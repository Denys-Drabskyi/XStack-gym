package org.example.controller.advice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;
import org.example.exception.AuthFailedException;
import org.example.exception.EntityNotFoundException;
import org.example.exception.ServiceResponseException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

/**
 * generated
 */

@ExtendWith(MockitoExtension.class)
public class ControllerAdviceTest {

  @Mock
  private WebRequest webRequest;

  @InjectMocks
  private ControllerAdvice controllerAdvice;

  @Test
  public void testHandleEntityNotFoundException() {
    String exceptionMessage = "Entity not found";
    Exception exception = new EntityNotFoundException(exceptionMessage);

    when(webRequest.getDescription(false)).thenReturn("/test");

    ResponseEntity<Object> responseEntity = controllerAdvice.handleEntityNotFoundException(exception, webRequest);

    assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
  }

  @Test
  public void testHandleAuthFailedException() {
    String exceptionMessage = "Authentication failed";
    Exception exception = new AuthFailedException(exceptionMessage);

    when(webRequest.getDescription(false)).thenReturn("/test");

    ResponseEntity<Object> responseEntity = controllerAdvice.handleAuthFailedException(exception, webRequest);

    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
  }

  @Test
  public void testHandleRuntimeException() {
    String exceptionMessage = "Authentication failed";
    Exception exception = new RuntimeException(exceptionMessage);

    when(webRequest.getDescription(false)).thenReturn("/test");

    ResponseEntity<Object> responseEntity = controllerAdvice.handleRuntimeException(exception, webRequest);

    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
  }

  @Test
  public void testHandleServiceResponseException() {
    String exceptionMessage = "Authentication failed";
    Exception exception = new ServiceResponseException(exceptionMessage);

    when(webRequest.getDescription(false)).thenReturn("/test");

    ResponseEntity<Object> responseEntity = controllerAdvice.handleServiceResponseException(exception, webRequest);

    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
  }

  // Add similar tests for other exception handlers...

  @Test
  public void testHandleMethodArgumentNotValid() {
    MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
    BindingResult bindingResult = mock(BindingResult.class);
    FieldError fieldError = new FieldError("objectName", "field", "defaultMessage");

    when(ex.getBindingResult()).thenReturn(bindingResult);
    when(bindingResult.getFieldErrors()).thenReturn(Collections.singletonList(fieldError));
    when(webRequest.getDescription(false)).thenReturn("/test");

    ResponseEntity<Object> responseEntity =
        controllerAdvice.handleMethodArgumentNotValid(ex, new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);

    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
  }
}
