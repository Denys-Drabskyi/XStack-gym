package org.example.service;

import java.util.UUID;
import org.example.dto.TrainerDto;
import org.example.entity.Trainer;

public interface TrainerService {
  TrainerDto getById(UUID id);
  Trainer create(Trainer trainer);
  TrainerDto create(TrainerDto trainer);
  Trainer update(Trainer trainer);
  TrainerDto update(TrainerDto trainer);
}
