package org.example.service.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.TrainingType;
import org.example.exception.EntityNotFoundException;
import org.example.repository.TrainingTypeRepository;
import org.example.service.TrainingTypeService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrainingTypeServiceImpl implements TrainingTypeService {

  private final TrainingTypeRepository repository;

  @Override
  public List<TrainingType> getByName(Collection<String> names) {
    log.info("Getting training types by names");
    List<TrainingType> types = repository.getByNameIn(names);
    if (types.size() != names.size()) {
      List<String> diff = names.stream().filter(name -> !getTypes().removeIf(type -> type.getName().equals(name)))
          .toList();

      throw EntityNotFoundException.types(diff);
    }
    return types;
  }

  @Override
  public TrainingType getByName(String name) {
    log.info("Getting training type with name:{}", name);
    return repository.getByName(name)
        .orElseThrow(() -> EntityNotFoundException.types(Collections.singletonList(name)));
  }

  @Override
  public List<TrainingType> getTypes() {
    log.info("Getting all training types");
    return repository.findAll();
  }
}
