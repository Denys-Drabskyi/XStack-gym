package org.example.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.example.dto.TrainerDto;
import org.example.entity.Trainer;
import org.example.entity.TrainingType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TrainerMapperTest {
  @Mock
  TrainingTypeMapper trainingTypeMapper;

  @Mock
  UserMapper userMapper;
  @InjectMocks
  TrainerMapperImpl trainerMapper;

  Trainer trainer = Trainer.builder().build();
  TrainerDto trainerDto = TrainerDto.builder().build();
  @Test
  void name() {
    trainerMapper.toDto(trainer);
    trainerMapper.toBuilder(trainerDto);
    trainerMapper.updateEntityFromDto(trainerDto, trainer);
    trainerMapper.toDtoList(List.of(trainer));
  }
}