package org.example.mapper;

import java.util.List;
import org.example.dto.TrainerDto;
import org.example.dto.TrainerDtoWithTrainees;
import org.example.dto.UserCredentialsDto;
import org.example.entity.Trainer;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

//@Mapper(componentModel = "spring", uses = {UserMapper.class, TrainingTypeMapper.class, TraineeMapper.class})
public interface TrainerMapper {
  Trainer.TrainerBuilder toBuilder(TrainerDto dto);

  @Mapping(target = "username", source = "user.username")
  @Mapping(target = "firstName", source = "user.firstName")
  @Mapping(target = "lastName", source = "user.lastName")
  @Mapping(target = "active", source = "user.active")
  TrainerDto toDto(Trainer entity);

  @Mapping(target = "username", source = "user.username")
  @Mapping(target = "password", source = "user.password")
  UserCredentialsDto toCredentials(Trainer entity);

  @DoIgnore
  @Mapping(target = "username", source = "user.username")
  @Mapping(target = "firstName", source = "user.firstName")
  @Mapping(target = "lastName", source = "user.lastName")
  @Mapping(target = "active", source = "user.active")
  TrainerDtoWithTrainees toDtoWithTrainees(Trainer entity);

  @Mapping(target = "specialization", ignore = true)
  @Mapping(target = "id", ignore = true)
  void updateEntityFromDto(TrainerDto from, @MappingTarget Trainer target);

  List<TrainerDto> toDtoList(List<Trainer> trainers);
}
