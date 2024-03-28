package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.aop.Auth;
import org.example.dto.OnAuth;
import org.example.dto.PasswordChangeDto;
import org.example.dto.UserCredentialsDto;
import org.example.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  @Auth
  @GetMapping("/login")
  public ResponseEntity<Void> login(@Validated(OnAuth.class) @RequestBody UserCredentialsDto credentials) {
    userService.auth(credentials);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @Auth
  @PutMapping("/password")
  public ResponseEntity<Void> passwordChange(@Validated(OnAuth.class) @RequestBody PasswordChangeDto credentials) {
    userService.updatePassword(credentials);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @Auth
  @PatchMapping("/activate")
  public ResponseEntity<Void> activateDeactivateUser(@Validated(OnAuth.class) @RequestBody UserCredentialsDto credentials) {
    userService.changeActive(credentials);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
