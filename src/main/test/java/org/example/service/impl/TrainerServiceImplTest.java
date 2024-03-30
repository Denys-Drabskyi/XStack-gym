package org.example.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;
import org.example.dao.TraineeDao;
import org.example.dao.TrainerDao;
import org.example.dto.TraineeDto;
import org.example.dto.TraineeDtoWithTrainers;
import org.example.dto.TrainerDto;
import org.example.dto.TrainerDtoWithTrainees;
import org.example.dto.UpdateTrainersListDto;
import org.example.dto.UserCredentialsDto;
import org.example.entity.Trainee;
import org.example.entity.Trainer;
import org.example.entity.User;
import org.example.mapper.TrainerMapper;
import org.example.repository.TraineeRepository;
import org.example.service.TrainingTypeService;
import org.example.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class TrainerServiceImplTest {
  private TrainerDto TRAINER_DTO;

  @BeforeEach
  void setUp() {
    TRAINER_DTO = TrainerDto.builder()
        .firstName("trainer")
        .lastName("trainer")
        .password("test")
        .specialization("Cardio")
        .build();
  }

  @Autowired
  private TraineeRepository traineeRepository;
  @Autowired
  private TrainerServiceImpl service;

  @Test
  @DisplayName("getByUsername() test")
  void testCase01() {
    TrainerDtoWithTrainees rez = service.getByUsername("trainer.trainer");
    assertEquals("trainer", rez.getFirstName());
    assertEquals(1, rez.getTrainees().size());
  }

  @Test
  @DisplayName("getByUsername() returns trainee")
  void testCase02() {
    TrainerDto rez = service.create(TRAINER_DTO);
    assertEquals("trainer.trainer1", rez.getUsername());
    assertEquals("Cardio", rez.getSpecialization());
  }

  @Test
  @DisplayName("update() test")
  void testCase03() {
    TRAINER_DTO.setUsername("trainer.trainer");
    TRAINER_DTO.setFirstName("new");
    TRAINER_DTO.setSpecialization("Yoga");

    TrainerDto rez = service.update(TRAINER_DTO);
    assertEquals("trainer.trainer", rez.getUsername());
    assertEquals("new", rez.getFirstName());
    assertEquals("trainer", rez.getLastName());
    assertEquals("Yoga", rez.getSpecialization());
  }

  @Test
  @DisplayName("getTrainersNotAssignedToTrainee() test")
  void testCase04() {
    List<TrainerDto> rez = service.getTrainersNotAssignedToTrainee("trainee.trainee");
    assertEquals(2, rez.size());
  }

  @ParameterizedTest
  @MethodSource("testCase05Source")
  @DisplayName("updateTrainers() test")
  void testCase05(List<String> trainers, UpdateTrainersListDto dto) {
    List<TrainerDto> rez = service.updateTrainers("trainee.trainee", dto);
    List<String> rezUsernames = rez.stream().map(UserCredentialsDto::getUsername).toList();
    assertEquals(trainers.size(), rezUsernames.size());
    assertTrue(trainers.containsAll(rezUsernames));
  }

  public static Stream<Arguments> testCase05Source() {
    return Stream.of(
        Arguments.of(List.of(), UpdateTrainersListDto.builder().build()),
        Arguments.of(List.of("trainer.trainer"), UpdateTrainersListDto.builder().trainers(List.of("trainer.trainer")).build()),
        Arguments.of(List.of("trainer.trainer"), UpdateTrainersListDto.builder().trainers(List.of("trainer.trainer", "inaccurate")).build()),
        Arguments.of(List.of("trainer.trainer", "trainer.train", "trainer.train1"), UpdateTrainersListDto.builder().trainers(List.of("trainer.trainer", "inaccurate", "trainer.train", "trainer.train1")).build())
    );
  }

}