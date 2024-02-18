package org.example.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.example.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserDaoTest {
  private Map<UUID, User> map;
  private UserDao dao;
  private static final User USER_1 = new User("firstName", "lastname");
  private static final User USER_2 = new User("firstName", "lastname1");
  private static final User USER_3 = new User("firstName", "lastnameSuffix");
  private static final User USER_4 = new User("prefixfirstName", "lastname99");

  @BeforeEach
  void setUp() {
    map = new HashMap<>();
    dao = new UserDao(map);
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
    map.put(USER_1.getId(), USER_1);

    assertTrue(dao.getLastWithUserNamePattern(USER_1.getUsername()).isPresent());
  }

  @Test
  @DisplayName("getLastWithUserNamePattern() should return optional with larger entity" +
      " when there is multiple users with username pattern matching")
  void testCase04() {
    map.put(USER_1.getId(), USER_1);
    map.put(USER_2.getId(), USER_2);

    Optional<User> user = dao.getLastWithUserNamePattern(USER_1.getUsername());
    assertTrue(user.isPresent());
    assertEquals(USER_2, user.get());
  }

  @Test
  @DisplayName("getLastWithUserNamePattern() should skip not numerical suffixes")
  void testCase05() {
    map.put(USER_1.getId(), USER_1);
    map.put(USER_2.getId(), USER_2);
    map.put(USER_3.getId(), USER_3);

    Optional<User> user = dao.getLastWithUserNamePattern(USER_1.getUsername());
    assertTrue(user.isPresent());
    assertEquals(USER_2, user.get());
  }

  @Test
  @DisplayName("getLastWithUserNamePattern() should skip prefixes")
  void testCase06() {
    map.put(USER_1.getId(), USER_1);
    map.put(USER_2.getId(), USER_2);
    map.put(USER_3.getId(), USER_3);
    map.put(USER_4.getId(), USER_4);

    Optional<User> user = dao.getLastWithUserNamePattern(USER_1.getUsername());
    assertTrue(user.isPresent());
    assertEquals(USER_2, user.get());
  }
}