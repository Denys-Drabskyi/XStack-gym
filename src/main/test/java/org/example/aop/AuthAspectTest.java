package org.example.aop;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.List;
import org.example.TestConfig;
import org.example.controller.TraineeController;
import org.example.controller.TrainerController;
import org.example.controller.TrainingController;
import org.example.controller.UserController;
import org.example.dto.PasswordChangeDto;
import org.example.dto.TraineeDto;
import org.example.dto.TrainerDto;
import org.example.dto.UpdateTrainersListDto;
import org.example.dto.UserCredentialsDto;
import org.example.exception.AuthFailedException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;


@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfig.class})
class AuthAspectTest {

  private static final UserCredentialsDto credentials = UserCredentialsDto.builder().build();

  private static final TraineeDto traineeDto = TraineeDto.builder().build();

  private static final TrainerDto trainerDto = TrainerDto.builder().build();

  @Autowired
  private TraineeController traineeController;
  @Autowired
  private TrainerController trainerController;
  @Autowired
  private TrainingController trainingController;
  @Autowired
  private UserController userController;

  @Test
  @DisplayName("TraineeService.get invokes AuthAspect.doAuth()")
  void testCase01() {
    assertThrows(AuthFailedException.class, () -> traineeController.getTrainee(credentials, credentials.getUsername()));
  }

  @Test
  @DisplayName("TraineeService.update invokes AuthAspect.doAuth()")
  void testCase02() {
    assertThrows(AuthFailedException.class, () -> traineeController.updateTrainee(traineeDto));
  }

  @Test
  @DisplayName("TraineeService.deleteByUsername invokes AuthAspect.doAuth()")
  void testCase03() {
    assertThrows(AuthFailedException.class, () -> traineeController.deleteTrainee(credentials));
  }

  @Test
  @DisplayName("TrainerService.get invokes AuthAspect.doAuth()")
  void testCase04() {
    assertThrows(AuthFailedException.class, () -> traineeController.updateTraineesTrainers(UpdateTrainersListDto.builder()
        .build()));
  }

  @Test
  @DisplayName("TrainerService.get invokes AuthAspect.doAuth()")
  void testCase05() {
    assertThrows(AuthFailedException.class, () -> traineeController.getNotAssignedActiveTrainers(credentials));
  }

  @Test
  @DisplayName("TrainerService.addTrainerToTrainee invokes AuthAspect.doAuth()")
  void testCase06() {
    assertThrows(AuthFailedException.class,
        () -> traineeController.getTraineeTrainings(credentials, List.of(), new Date(), new Date()));
  }

  @Test
  @DisplayName("TrainerService.getTrainersNotAssignedToTrainee invokes AuthAspect.doAuth()")
  void testCase07() {
    assertThrows(AuthFailedException.class, () -> trainerController.getTrainer(credentials, credentials.getUsername()));
  }

  @Test
  @DisplayName("TrainingService.getTraineeTrainingListByTrainerAndDateBetween invokes AuthAspect.doAuth()")
  void testCase08() {
    assertThrows(AuthFailedException.class, () -> trainerController.updateTrainer(trainerDto));
  }

  @Test
  @DisplayName("TrainingService.getTrainerTrainingListByTraineeAndDateBetween invokes AuthAspect.doAuth()")
  void testCase09() {
    assertThrows(AuthFailedException.class, () -> trainerController.getUnassignedTrainers(credentials));
  }

  @Test
  @DisplayName("TrainerService.addTrainerToTrainee invokes AuthAspect.doAuth()")
  void testCase10() {
    assertThrows(AuthFailedException.class,
        () -> trainerController.getTrainerTrainings(credentials, List.of(), new Date(), new Date()));
  }

  @Test
  @DisplayName("TrainerService.addTrainerToTrainee invokes AuthAspect.doAuth()")
  void testCase11() {
    assertThrows(AuthFailedException.class,
        () -> trainingController.getTypes(credentials));
  }

  @Test
  @DisplayName("TrainerService.addTrainerToTrainee invokes AuthAspect.doAuth()")
  void testCase12() {
    assertThrows(AuthFailedException.class,
        () -> userController.activateDeactivateUser(credentials));
  }

  @Test
  @DisplayName("TrainerService.addTrainerToTrainee invokes AuthAspect.doAuth()")
  void testCase13() {
    assertThrows(AuthFailedException.class,
        () -> userController.passwordChange(PasswordChangeDto.builder().build()));
  }

  @Test
  @DisplayName("TrainerService.addTrainerToTrainee invokes AuthAspect.doAuth()")
  void testCase14() {
    assertThrows(AuthFailedException.class,
        () -> userController.login(credentials));
  }
}