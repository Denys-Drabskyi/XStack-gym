package org.example.repository;

import java.util.Optional;
import java.util.UUID;
import org.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
  int countAllByActive(boolean active);
  @Query(value = "SELECT * FROM User u WHERE u.username LIKE :pattern " +
      "ORDER BY LENGTH(u.username) DESC, u.username DESC LIMIT 1", nativeQuery = true)
  Optional<User> lastWithPattern(String pattern);

  Optional<User> getByUsername(String username);

  boolean existsByUsernameAndPasswordAndActive(String username, String password, boolean active);
}
