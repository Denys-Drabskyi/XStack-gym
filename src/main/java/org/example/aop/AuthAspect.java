//package org.example.aop;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.example.dto.UserCredentialsDto;
//import org.example.exception.AuthFailedException;
//import org.example.service.UserService;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//@Slf4j
//@Aspect
//@Order(2)
//@Component
//@RequiredArgsConstructor
//public class AuthAspect {
//  private final UserService userService;
//
//  @Pointcut("@annotation(org.example.aop.Auth) && args(credentialsDto, ..)")
//  public void auth(UserCredentialsDto credentialsDto) {}
//
//  @Before(value = "auth(credentialsDto)", argNames = "credentialsDto")
//  public void doAuth(UserCredentialsDto credentialsDto) {
//    log.info("Started user:{} auth", credentialsDto.getUsername());
//    if (!userService.auth(credentialsDto)) {
//      log.error("User:{} is not authenticated", credentialsDto.getUsername());
//      throw AuthFailedException.forUser(credentialsDto);
//    }
//    log.debug("user:{} auth succ", credentialsDto.getUsername());
//  }
//}
