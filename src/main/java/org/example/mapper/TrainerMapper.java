package org.example.mapper;

import org.example.dto.TrainerDto;
import org.example.entity.Trainer;
import org.example.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TrainerMapper {

  @Mapping(target = "userId", ignore = true)
  TrainerDto fromUser(User user);

  Trainer newTrainerFromDto(TrainerDto dto);

  void updateDtoFromEntity(Trainer trainee, @MappingTarget TrainerDto dto);

  void updateEntityFromEntity(Trainer from, @MappingTarget Trainer target);
}
