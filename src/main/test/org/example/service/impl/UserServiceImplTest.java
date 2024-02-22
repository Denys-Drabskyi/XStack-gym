package org.example.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.example.dao.UserDao;
import org.example.entity.Trainer;
import org.example.entity.TrainingType;
import org.example.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
  @Mock
  private UserDao userDao;
  @InjectMocks
  private UserServiceImpl service;
  private User user;

  @BeforeEach
  void setUp() {
    user = new Trainer("test", "test", TrainingType.TYPE_1);
  }

  @Test
  @DisplayName("checkForUsernameAvailable() do not call updateSuffix() when there is no user with this username pattern")
  void testCase01() {
    when(userDao.getLastWithUserNamePattern(any())).thenReturn(Optional.empty());

    service.checkForUsernameAvailable(user);
    assertEquals("test.test" ,user.getUsername());
  }

  @Test
  @DisplayName("checkForUsernameAvailable() calls updateSuffix() when there is no user with this username pattern")
  void testCase02() {
    when(userDao.getLastWithUserNamePattern(any())).thenReturn(Optional.ofNullable(user));

    service.checkForUsernameAvailable(user);
    assertEquals("test.test1" ,user.getUsername());
  }
}