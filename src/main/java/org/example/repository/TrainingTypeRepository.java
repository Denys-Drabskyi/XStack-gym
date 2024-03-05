package org.example.repository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import org.example.entity.TrainingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingTypeRepository extends JpaRepository<TrainingType, UUID> {
  List<TrainingType> getByNameIn(Collection<String> name);
  TrainingType getByName(String name);
}
