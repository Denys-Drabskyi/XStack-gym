package org.example.mapper;

import org.example.entity.Trainee;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TraineeMapper {

  void updateEntityFromEntity(Trainee from, @MappingTarget Trainee target);
}
