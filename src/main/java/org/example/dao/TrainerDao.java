package org.example.dao;

import java.util.Map;
import java.util.UUID;
import org.example.entity.Trainer;
import org.springframework.stereotype.Component;

@Component
public class TrainerDao extends BasicMapDao<UUID, Trainer>{
  public TrainerDao(Map<UUID, Trainer>  trainerStorage) {
    super(trainerStorage, Trainer.class.getName());
  }
}
