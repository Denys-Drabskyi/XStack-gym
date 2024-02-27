package org.example.service;

import java.util.Optional;
import java.util.UUID;
import org.example.entity.Trainer;

public interface TrainerService {
  Optional<Trainer> getById(UUID id);
  Trainer getExistingById(UUID id);
  Trainer create(Trainer trainer);
  Trainer update(Trainer trainer);
}
