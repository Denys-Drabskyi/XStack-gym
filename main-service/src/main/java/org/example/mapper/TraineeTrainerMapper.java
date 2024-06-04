package org.example.mapper;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserMapper.class, TrainingTypeMapper.class})
public interface TraineeTrainerMapper extends TraineeMapper, TrainerMapper {
}
