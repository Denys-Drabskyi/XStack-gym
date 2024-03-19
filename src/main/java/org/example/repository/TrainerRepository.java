package org.example.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.example.entity.Trainee;
import org.example.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, UUID> {
  Optional<Trainer> getByUserUsername(String username);
  List<Trainer> getTrainersByTraineesNotContainingAndUserActive(Trainee trainees, boolean active);

  default List<Trainer> getTrainersNotAssignedToTrainee(Trainee trainee) {
    return getTrainersByTraineesNotContainingAndUserActive(trainee, true);
  }
}
