package org.example.repository;

import java.util.Optional;
import java.util.UUID;
import org.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
  Optional<User> findTopByUsernameLikeOrderByUsernameDesc(String pattern);

  Optional<User> getByUsername(String username);

  boolean existsByUsernameAndPasswordAndActive(String username, String password, boolean active);
}
