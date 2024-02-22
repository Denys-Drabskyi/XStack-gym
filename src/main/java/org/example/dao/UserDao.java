package org.example.dao;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.Trainee;
import org.example.entity.Trainer;
import org.example.entity.User;
import org.example.exception.SuffixUpdateException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserDao {

  private final Map<UUID, Trainee> traineeStorage;
  private final Map<UUID, Trainer> trainerStorage;

  private static final String REGEX = "%s\\d*";

  public UserDao(Map<UUID, Trainee> traineeStorage, Map<UUID, Trainer> trainerStorage) {
    this.traineeStorage = traineeStorage;
    this.trainerStorage = trainerStorage;
  }

  public Optional<User> getLastWithUserNamePattern(String username) {
    try {
      log.info("Started looking for users with username:{}", username);
      String pattern = String.format(REGEX, username);
      return Stream.concat(traineeStorage.values().stream(), trainerStorage.values().stream())
          .filter(t -> t.getUsername().matches(pattern))
          .max(Comparator.comparing(User::getUsername));
    } catch (Exception e){
      log.error("Exception during users with username containing:{} finding process", username);
      throw SuffixUpdateException.duringFiltering(username, e);
    }
  }

  public boolean existsById(UUID id) {
    log.info("Started looking for users with id{}", id);
    return Stream.concat(traineeStorage.values().stream(), trainerStorage.values().stream())
        .anyMatch(t -> t.getId().equals(id));
  }
}
