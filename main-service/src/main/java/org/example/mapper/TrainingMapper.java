package org.example.mapper;

import java.util.List;
import org.example.dto.ActionType;
import org.example.dto.RegisterTrainingEventDto;
import org.example.dto.TrainingDto;
import org.example.entity.Training;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {TraineeMapper.class, TrainerMapper.class, TrainingTypeMapper.class})
public interface TrainingMapper {

  @Mapping(target = "type", source = "trainingType")
  @Mapping(target = "trainee.user.username", source = "traineeUsername")
  @Mapping(target = "trainer.user.username", source = "trainerUsername")
  Training toEntity(TrainingDto dto);

  @Mapping(target = "trainingType", source = "type")
  @Mapping(target = "traineeUsername", source = "trainee.user.username")
  @Mapping(target = "trainerUsername", source = "trainer.user.username")
  TrainingDto toDto(Training entity);

  @Mapping(target = "username", source = "training.trainer.user.username")
  @Mapping(target = "firstname", source = "training.trainer.user.firstName")
  @Mapping(target = "lastname", source = "training.trainer.user.lastName")
  @Mapping(target = "isActive", source = "training.trainer.user.active")
  @Mapping(target = "trainingDuration", source = "training.duration")
  @Mapping(target = "trainingDate", source = "training.date")
  RegisterTrainingEventDto toRegisterTrainingEventDto(Training training, ActionType actionType);

  List<TrainingDto> toDtoList(List<Training> entity);
}
