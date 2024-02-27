package org.example.entity;

import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.example.exception.SuffixUpdateException;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public abstract class User implements Entity<UUID> {
  @Setter(AccessLevel.NONE)
  private UUID id;
  private String firstName;
  private String lastName;
  @Setter(AccessLevel.NONE)
  private String username;
  private String password;
  private boolean isActive;

  @Override
  public UUID getId() {
    return id;
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
