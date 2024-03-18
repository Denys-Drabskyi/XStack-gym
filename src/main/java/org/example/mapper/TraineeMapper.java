package org.example.mapper;

import org.example.dto.TraineeDto;
import org.example.entity.Trainee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface TraineeMapper {
  Trainee.TraineeBuilder toBuilder(TraineeDto dto);

  @Mapping(target = "username", source = "user.username")
  @Mapping(target = "firstName", source = "user.firstName")
  @Mapping(target = "lastName", source = "user.lastName")
  @Mapping(target = "isActive", source = "user.active")
  TraineeDto toDto(Trainee entity);

  @Mapping(target = "id", ignore = true)
  void updateEntityFromDto(TraineeDto from, @MappingTarget Trainee target);
}
