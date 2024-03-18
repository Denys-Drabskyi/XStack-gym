package org.example.mapper;

import org.example.dto.TrainingDto;
import org.example.entity.Training;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {TraineeMapper.class, TrainerMapper.class, TrainingTypeMapper.class})
public interface TrainingMapper {

  @Mapping(target = "type", source = "trainingType")
  @Mapping(target = "trainee", source = "traineeDto")
  @Mapping(target = "trainer", source = "trainerDto")
  Training toEntity(TrainingDto dto);

  @Mapping(target = "trainingType", source = "type")
  @Mapping(target = "traineeDto", source = "trainee")
  @Mapping(target = "trainerDto", source = "trainer")
  TrainingDto toDto(Training entity);
}
