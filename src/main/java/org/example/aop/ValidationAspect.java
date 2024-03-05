package org.example.aop;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidatorFactory;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.example.dto.UserCredentialsDto;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Order(1)
@Component
@RequiredArgsConstructor
public class ValidationAspect {
  private final ValidatorFactory factory;

  @Pointcut("execution(public * org.example.service.*.*(..)) && args(credentialsDto)")
  public void validation(UserCredentialsDto credentialsDto) {}

  @Before(value = "validation(credentialsDto)", argNames = "credentialsDto")
  public void validate(UserCredentialsDto credentialsDto) {
    Set<ConstraintViolation<UserCredentialsDto>> rez = factory.getValidator().validate(credentialsDto);

    if (!rez.isEmpty()){
      throw new ConstraintViolationException(rez);
    }
  }
}
