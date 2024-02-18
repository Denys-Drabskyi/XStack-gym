package org.example.dao;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.User;
import org.example.exception.SuffixUpdateException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserDao extends BasicMapDao<UUID, User>{

  private static final String REGEX = "%s\\d*";

  public UserDao(Map<UUID, User> storage) {
    super(storage, User.class.getSimpleName());
  }

  public Optional<User> getLastWithUserNamePattern(String username) {
    try {
      log.info("Started looking for users with username:{}", username);
      String pattern = String.format(REGEX, username);
      return storage.values().stream()
          .filter(t -> t.getUsername().matches(pattern))
          .max(Comparator.comparing(User::getUsername));
    } catch (Exception e){
      log.error("Exception during users with username containing:{} finding process", username);
      throw SuffixUpdateException.duringFiltering(username, e);
    }
  }
}
