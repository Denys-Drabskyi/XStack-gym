package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.configuration.security.LogoutBlacklist;
import org.example.dto.OnAuth;
import org.example.dto.PasswordChangeDto;
import org.example.dto.UserCredentialsDto;
import org.example.service.AuthService;
import org.example.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
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
  private final AuthService authService;
  private final LogoutBlacklist blacklist;

  @GetMapping("/login")
  public ResponseEntity<String> login(@Validated(OnAuth.class) @RequestBody UserCredentialsDto credentials) {
    return ResponseEntity.ok(authService.signIn(credentials));
  }

  @GetMapping("/logout")
  public void logout(HttpServletRequest request) {
    String token = extractTokenFromRequest(request);
    blacklist.addToBlacklist(token);
  }

  public String extractTokenFromRequest(HttpServletRequest request) {
    String authorizationHeader = request.getHeader("Authorization");

    if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
      return authorizationHeader.substring(7);
    }

    return null;
  }

  @PutMapping("/password")
  public ResponseEntity<Void> passwordChange(@Validated(OnAuth.class) @RequestBody PasswordChangeDto credentials) {
    userService.updatePassword(credentials);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @PatchMapping("/activate")
  public ResponseEntity<Void> activateDeactivateUser(@AuthenticationPrincipal UserDetails userDetails) {
    userService.changeActive(userDetails.getUsername());
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
