package org.example.service;

import java.util.Collection;
import java.util.List;
import org.example.entity.TrainingType;

public interface TrainingTypeService {
  List<TrainingType> getByName(Collection<String> names);
  TrainingType getByName(String name);
}
