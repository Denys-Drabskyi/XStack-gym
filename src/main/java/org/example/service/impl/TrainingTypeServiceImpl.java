package org.example.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.example.entity.TrainingType;
import org.example.repository.TrainingTypeRepository;
import org.example.service.TrainingTypeService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrainingTypeServiceImpl implements TrainingTypeService {

  private final TrainingTypeRepository repository;

  @Override
  public List<TrainingType> getByName(Collection<String> names) {
    return repository.getByNameIn(names);
  }

  @Override
  public TrainingType getByName(String name) {
    return repository.getByName(name);
  }
}
