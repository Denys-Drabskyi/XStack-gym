package org.example.service;

import java.util.List;
import org.example.entity.TrainingType;

public interface TrainingTypeService {
  TrainingType getByName(String name);
  List<TrainingType> getTypes();
}
