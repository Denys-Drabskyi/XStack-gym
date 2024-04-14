package org.example.repository;

import java.util.Optional;
import java.util.UUID;
import org.example.entity.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TraineeRepository extends JpaRepository<Trainee, UUID> {
  Optional<Trainee> getByUserUsername(String username);
}
