package org.example.service;

import java.util.UUID;
import org.example.entity.User;

public interface UserService {
  boolean existsById(UUID id);
  void checkForUsernameAvailable(User user);
}
