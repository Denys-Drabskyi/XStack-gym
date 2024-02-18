package org.example.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.example.exception.SuffixUpdateException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {

  @Test
  @DisplayName("addSuffixToUserNameAfter() should throw SuffixUpdateException" +
      " when received username does not matching actual")
  void testCase01(){
    User user = new User("test", "test");
    assertThrows(SuffixUpdateException.class, () -> user.addSuffixToUserNameAfter("test.middle.test"));
  }

  @Test
  @DisplayName("addSuffixToUserNameAfter() should set suffix to 1 if received username has none")
  void testCase03(){
    User user = new User("test", "test");

    user.addSuffixToUserNameAfter("test.test");
    assertEquals("test.test1", user.getUsername());
  }

  @Test
  @DisplayName("addSuffixToUserNameAfter() should update suffix")
  void testCase04(){
    User user = new User("test", "test");

    user.addSuffixToUserNameAfter("test.test4");
    assertEquals("test.test5", user.getUsername());
  }

  @Test
  @DisplayName("setPassword() throws NullPointerException when provided null password")
  void testCase05(){
    User user = new User("test", "test");

    assertThrows(NullPointerException.class, () -> user.setPassword(null));
  }

  @Test
  @DisplayName("setPassword() updates password")
  void testCase06(){
    User user = new User("test", "test");

    user.setPassword("null");
    assertEquals("null", user.getPassword());
  }

  @Test
  @DisplayName("setLastName() throws NullPointerException when provided null lastname")
  void testCase07(){
    User user = new User("test", "test");

    assertThrows(NullPointerException.class, () -> user.setLastName(null));
  }

  @Test
  @DisplayName("setLastName() updates password")
  void testCase08(){
    User user = new User("test", "test");

    user.setLastName("null");
    assertEquals("null", user.getLastName());
  }

  @Test
  @DisplayName("setFirstName() throws NullPointerException when provided null lastname")
  void testCase09(){
    User user = new User("test", "test");

    assertThrows(NullPointerException.class, () -> user.setFirstName(null));
  }

  @Test
  @DisplayName("setFirstName() updates password")
  void testCase10(){
    User user = new User("test", "test");

    user.setFirstName("null");
    assertEquals("null", user.getFirstName());
  }
}