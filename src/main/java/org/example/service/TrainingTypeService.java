package org.example.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.example.entity.TrainingType;

public interface TrainingTypeService {
  List<TrainingType> getByName(Collection<String> names);
  TrainingType getByName(String name);
}
