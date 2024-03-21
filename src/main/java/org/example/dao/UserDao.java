package org.example.dao;

import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.User;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserDao extends BasicDao<UUID, User, UserRepository> {
  private static final String SQL_LIKE = "%s%%";

  public UserDao(UserRepository repository) {
    super(repository, User.class.getSimpleName());
  }

  public int activeUsers() {
    return repository.countAllByActive(true);
  }

  public Optional<User> getLastWithUserNamePattern(String username) {
    log.info("Started looking for users with username:{}", username);
    String pattern = String.format(SQL_LIKE, username);
    return repository.lastWithPattern(pattern);
  }

  public Optional<User> getByUsername(String username) {
    return repository.getByUsername(username);
  }

  public boolean existsByUsernameAndPasswordAndActive(String username, String password, boolean active) {
    return repository.existsByUsernameAndPasswordAndActive(username, password, active);
  }
}
