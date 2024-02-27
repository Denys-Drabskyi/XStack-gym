package org.example.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.example.entity.Trainee;
import org.example.entity.Trainer;
import org.example.entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserDaoTest {
  private Map<UUID, Trainer> trainerMap;
  private Map<UUID, Trainee> traineeMap;
  private UserDao dao;
  private static Trainee USER_1;
  private static Trainee USER_2;
  private static Trainee USER_3;
  private static Trainee USER_4;
  private static Trainer USER_5;
  private static Trainer USER_6;

  @BeforeAll
  static void beforeAll() {
    USER_1 = Trainee.builder().id(UUID.randomUUID()).username("firstName.lastname").build();
    USER_2 = Trainee.builder().id(UUID.randomUUID()).username("firstName.lastname1").build();
    USER_3 = Trainee.builder().id(UUID.randomUUID()).username("firstName.lastnameSuffix").build();
    USER_4 = Trainee.builder().id(UUID.randomUUID()).username("prefixfirstName.lastname99").build();
    USER_5 = Trainer.builder().id(UUID.randomUUID()).username("firstName.lastname").build();
    USER_6 = Trainer.builder().id(UUID.randomUUID()).username("firstName.lastname1").build();
  }

  @BeforeEach
  void setUp() {
    trainerMap = new HashMap<>();
    traineeMap = new HashMap<>();
    dao = new UserDao(traineeMap, trainerMap);
  }

  @Test
  @DisplayName("getLastWithUserNamePattern() should return empty optional" +
      " when there is no users with username pattern matching")
  void testCase01() {
    assertTrue(dao.getLastWithUserNamePattern("").isEmpty());
  }

  @Test
  @DisplayName("getLastWithUserNamePattern() should return empty optional" +
      " when there is no users with username pattern matching")
  void testCase02() {
    assertTrue(dao.getLastWithUserNamePattern("").isEmpty());
  }

  @Test
  @DisplayName("getLastWithUserNamePattern() should return optional with entity" +
      " when there is user with username pattern matching")
  void testCase03() {
    traineeMap.put(USER_1.getId(), USER_1);

    assertTrue(dao.getLastWithUserNamePattern(USER_1.getUsername()).isPresent());
  }

  @Test
  @DisplayName("getLastWithUserNamePattern() should return optional with larger entity" +
      " when there is multiple users with username pattern matching")
  void testCase04() {
    traineeMap.put(USER_1.getId(), USER_1);
    traineeMap.put(USER_2.getId(), USER_2);

    Optional<User> user = dao.getLastWithUserNamePattern(USER_1.getUsername());
    assertTrue(user.isPresent());
    assertEquals(USER_2, user.get());
  }

  @Test
  @DisplayName("getLastWithUserNamePattern() should skip not numerical suffixes")
  void testCase05() {
    traineeMap.put(USER_1.getId(), USER_1);
    traineeMap.put(USER_2.getId(), USER_2);
    traineeMap.put(USER_3.getId(), USER_3);

    Optional<User> user = dao.getLastWithUserNamePattern(USER_1.getUsername());
    assertTrue(user.isPresent());
    assertEquals(USER_2, user.get());
  }

  @Test
  @DisplayName("getLastWithUserNamePattern() should skip prefixes")
  void testCase06() {
    traineeMap.put(USER_1.getId(), USER_1);
    traineeMap.put(USER_2.getId(), USER_2);
    traineeMap.put(USER_3.getId(), USER_3);
    traineeMap.put(USER_4.getId(), USER_4);

    Optional<User> user = dao.getLastWithUserNamePattern(USER_1.getUsername());
    assertTrue(user.isPresent());
    assertEquals(USER_2, user.get());
  }

  @Test
  @DisplayName("getLastWithUserNamePattern() checks both storages for values and returns trainee")
  void testCase07() {
    traineeMap.put(USER_2.getId(), USER_2);
    trainerMap.put(USER_5.getId(), USER_5);


    Optional<User> user = dao.getLastWithUserNamePattern(USER_1.getUsername());
    assertTrue(user.isPresent());
    assertEquals(USER_2, user.get());
  }

  @Test
  @DisplayName("getLastWithUserNamePattern() checks both storages for values and returns trainer")
  void testCase08() {
    traineeMap.put(USER_1.getId(), USER_1);
    trainerMap.put(USER_6.getId(), USER_6);

    Optional<User> user = dao.getLastWithUserNamePattern(USER_1.getUsername());
    assertTrue(user.isPresent());
    assertEquals(USER_6, user.get());
  }
}