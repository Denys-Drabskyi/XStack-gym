package org.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.exception.SuffixUpdateException;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "User")
public class User implements IdEntity<UUID> {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Setter(AccessLevel.NONE)
  private UUID id;

  @Column(nullable = false)
  private String firstName;
  @Column(nullable = false)
  private String lastName;
  @Column(nullable = false, unique = true)
  @Setter(AccessLevel.NONE)
  private String username;
  @Column(nullable = false)
  private String password;
  @Column(nullable = false)
  private boolean active;

  @Override
  public UUID getId() {
    return id;
  }

  public void addSuffixToUserNameAfter(String username) {
    String patternString = String.format("(?i)%s\\d*", this.username);
    if (!username.matches(patternString)) {
      throw SuffixUpdateException.wrongUsername(this.username, username);
    }
    patternString = String.format("(?i)%s", this.username);
    Pattern pattern = Pattern.compile(patternString);
    Matcher matcher = pattern.matcher(username);
    String suffix = matcher.replaceAll("");
    if (suffix.equals("")) {
      suffix = "0";
    }
    this.username = this.username.concat(String.valueOf(Integer.parseInt(suffix) + 1));
  }

  public static class UserBuilder {
    public UserBuilder() {}
  }
}
