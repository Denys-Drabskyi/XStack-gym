package org.example.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;
import org.example.exception.SuffixUpdateException;
import org.example.util.PasswordGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {
  private User user;

  @BeforeEach
  void setUp() {
    user = User.builder()
        .id(UUID.randomUUID())
        .firstName("test")
        .lastName("test")
        .username("test.test")
        .password(PasswordGenerator.generatePassword())
        .active(true)
        .build()
    ;
  }

  @Test
  @DisplayName("addSuffixToUserNameAfter() should throw SuffixUpdateException" +
      " when received username does not matching actual")
  void testCase01(){
    assertThrows(SuffixUpdateException.class, () -> user.addSuffixToUserNameAfter("test.middle.test"));
  }

  @Test
  @DisplayName("addSuffixToUserNameAfter() should set suffix to 1 if received username has none")
  void testCase03(){
    user.addSuffixToUserNameAfter("test.test");
    assertEquals("test.test1", user.getUsername());
  }

  @Test
  @DisplayName("addSuffixToUserNameAfter() should update suffix")
  void testCase04(){
    user.addSuffixToUserNameAfter("test.test4");
    assertEquals("test.test5", user.getUsername());
  }

  @Test
  @DisplayName("setPassword() updates password")
  void testCase06(){
    user.setPassword("null");
    assertEquals("null", user.getPassword());
  }

  @Test
  @DisplayName("setLastName() updates password")
  void testCase08(){
    user.setLastName("null");
    assertEquals("null", user.getLastName());
  }

  @Test
  @DisplayName("setFirstName() updates password")
  void testCase10(){
    user.setFirstName("null");
    assertEquals("null", user.getFirstName());
  }
}