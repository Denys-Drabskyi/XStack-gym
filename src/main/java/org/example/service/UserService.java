package org.example.service;

import java.util.UUID;
import org.example.dto.PasswordChangeDto;
import org.example.dto.UserDto;
import org.example.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
  boolean existsById(UUID id);

  UserDetailsService userDetailsService();

  User createUser(UserDto trainee);
  void update(UserDto dto, User user);

  User getByUsername(String username);

  void updatePassword(PasswordChangeDto dto);

  void changeActive(String username);
}
