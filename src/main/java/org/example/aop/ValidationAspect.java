package org.example.aop;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.example.dto.ValidatedDto;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Order(1)
@Component
@RequiredArgsConstructor
public class ValidationAspect {
  private final Validator validator;

  @Pointcut("execution(public * org.example.service.*.*(..)) && args(validatedDto, ..)" +
      " && !execution(public boolean org.example.service.UserService.auth(..))")
  public void validation(ValidatedDto validatedDto) {}

  @Before(value = "validation(validatedDto)", argNames = "validatedDto")
  public void validate(ValidatedDto validatedDto) {
    Set<ConstraintViolation<ValidatedDto>> rez = validator.validate(validatedDto);
    if (!rez.isEmpty()){
      throw new ConstraintViolationException(rez);
    }
  }
}