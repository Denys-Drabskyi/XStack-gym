package org.example.service;

import java.util.Optional;
import java.util.UUID;
import org.example.dto.UserDto;
import org.example.entity.User;

public interface UserService {
  Optional<User> getById(UUID id);
  User getExistingById(UUID id);
  User create(User user);
  User create(UserDto user);
  User update(User user);
  User update(UserDto userDto);
  void deleteById(UUID id);
}
