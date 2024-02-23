package org.example.mapper;

import org.example.entity.Trainee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TraineeMapper {
  @Mapping(source = "active", target = "isActive")
  Trainee.TraineeBuilder toBuilder (Trainee trainee);
  @Mapping(target = "username", ignore = true)
  void updateEntityFromEntity(Trainee from, @MappingTarget Trainee target);
}
