package org.example.service;

import java.util.UUID;
import org.example.dto.PasswordChangeDto;
import org.example.dto.UserCredentialsDto;
import org.example.dto.UserDto;
import org.example.entity.User;

public interface UserService {
  boolean existsById(UUID id);
  User createUser(UserDto trainee);
  void update(UserDto dto, User user);

  void updatePassword(PasswordChangeDto dto);

  void activate(UserCredentialsDto dto);

  void deactivate(UserCredentialsDto dto);

  boolean auth(UserCredentialsDto dto);
}
