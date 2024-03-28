package org.example.mapper;

import org.example.dto.TraineeDto;
import org.example.dto.TraineeDtoWithTrainers;
import org.example.entity.Trainee;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

//@Mapper(componentModel = "spring", uses = {UserMapper.class, TrainerMapper.class})
public interface TraineeMapper {
  Trainee.TraineeBuilder toBuilder(TraineeDto dto);

  @Mapping(target = "username", source = "user.username")
  @Mapping(target = "password", source = "user.password")
  @Mapping(target = "firstName", source = "user.firstName")
  @Mapping(target = "lastName", source = "user.lastName")
  @Mapping(target = "active", source = "user.active")
  TraineeDto toDto(Trainee entity);

  @DoIgnore
  @Mapping(target = "username", source = "user.username")
  @Mapping(target = "password", source = "user.password")
  @Mapping(target = "firstName", source = "user.firstName")
  @Mapping(target = "lastName", source = "user.lastName")
  @Mapping(target = "active", source = "user.active")
  TraineeDtoWithTrainers toDtoWithTrainers(Trainee entity);

  @Mapping(target = "id", ignore = true)
  void updateEntityFromDto(TraineeDto from, @MappingTarget Trainee target);
}
