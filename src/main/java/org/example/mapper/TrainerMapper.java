package org.example.mapper;

import java.util.List;
import org.example.dto.TrainerDto;
import org.example.entity.Trainer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {UserMapper.class, TrainingTypeMapper.class})
public interface TrainerMapper {
  Trainer.TrainerBuilder toBuilder(TrainerDto dto);

  @Mapping(target = "username", source = "user.username")
  @Mapping(target = "firstName", source = "user.firstName")
  @Mapping(target = "lastName", source = "user.lastName")
  @Mapping(target = "isActive", source = "user.active")
  TrainerDto toDto(Trainer entity);

  @Mapping(target = "specializations", ignore = true)
  @Mapping(target = "id", ignore = true)
  void updateEntityFromDto(TrainerDto from, @MappingTarget Trainer target);

  List<TrainerDto> toDtoList(List<Trainer> trainers);
}
