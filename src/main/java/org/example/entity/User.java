package org.example.entity;

import java.util.Objects;
import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.example.exception.SuffixUpdateException;
import org.example.util.PasswordGenerator;

@Getter
@EqualsAndHashCode
public class User implements Entity<UUID> {
  private final UUID id;
  private String firstName;
  private String lastName;
  private String username;
  private String password;
  private boolean isActive;

  public User(String firstName, String lastName) {
    Objects.requireNonNull(firstName, "First name can not be null");
    Objects.requireNonNull(lastName, "Last name can not be null");
    this.id = UUID.randomUUID();
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = String.format("%s.%s", firstName, lastName);
    this.password = new PasswordGenerator().generatePassword();
    this.isActive = true;
  }

  @Override
  public UUID getId() {
    return id;
  }

  public void setFirstName(String firstName) {
    Objects.requireNonNull(firstName, "First name can not be null");
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    Objects.requireNonNull(lastName, "Last name can not be null");
    this.lastName = lastName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password){
    Objects.requireNonNull(password, "Password name can not be null");
    this.password = password;
  }

  public void setActive(boolean active) {
    isActive = active;
  }

  public void addSuffixToUserNameAfter(String username) {
    String pattern = String.format("%s\\d*", this.username);
    if (!username.matches(pattern)) {
      throw SuffixUpdateException.wrongUsername(this.username, username);
    }
    String suffix = username.replace(this.getUsername(), "");
    if (suffix.equals("")) {
      suffix = "0";
    }
    this.username = this.username.concat(String.valueOf(Integer.parseInt(suffix) + 1));
  }
}
