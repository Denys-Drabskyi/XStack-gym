package org.example.mapper;

import org.example.dto.TraineeDto;
import org.example.entity.Trainee;
import org.example.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TraineeMapper {

  @Mapping(target = "userId", ignore = true)
  TraineeDto fromUser(User user);
  @Mapping(target = "userId", source = "userId")
  Trainee newTraineeFromDto(TraineeDto dto);

  void updateDtoFromEntity(Trainee trainee, @MappingTarget TraineeDto dto);

  void updateEntityFromEntity(Trainee from, @MappingTarget Trainee target);
}
