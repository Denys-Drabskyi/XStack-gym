package org.example.mapper;

import org.example.entity.Trainer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TrainerMapper {
  @Mapping(source = "active", target = "isActive")
  Trainer.TrainerBuilder toBuilder (Trainer trainer);

  void updateEntityFromEntity(Trainer from, @MappingTarget Trainer target);
}
