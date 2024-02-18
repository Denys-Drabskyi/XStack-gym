package org.example.service;

import java.util.UUID;
import org.example.entity.Training;

public interface TrainingService {
  Training getById(UUID id);
  Training create(Training training);
}
