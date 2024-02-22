package org.example.mapper;

import org.example.entity.Trainer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TrainerMapper {
  void updateEntityFromEntity(Trainer from, @MappingTarget Trainer target);
}
