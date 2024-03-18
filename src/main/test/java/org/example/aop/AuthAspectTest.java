package org.example.aop;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.example.TestConfig;
import org.example.dto.TraineeDto;
import org.example.dto.TrainerDto;
import org.example.dto.UserCredentialsDto;
import org.example.exception.AuthFailedException;
import org.example.service.TraineeService;
import org.example.service.TrainerService;
import org.example.service.TrainingService;
import org.example.service.UserService;
import org.example.service.impl.TraineeServiceImpl;
import org.example.service.impl.TrainerServiceImpl;
import org.example.service.impl.TrainingServiceImpl;
import org.example.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

class AuthAspectTest {

  private static final UserCredentialsDto credentials = UserCredentialsDto.builder().build();

  private static final TraineeDto traineeDto = TraineeDto.builder().build();

  private static final TrainerDto trainerDto = TrainerDto.builder().build();

  private static TraineeService traineeService;

  private static TrainerService trainerService;

  private static TrainingService trainingService;

  @BeforeAll
  static void setUp() {
    UserService userService = mock(UserService.class);
    when(userService.auth(any())).thenReturn(false);

    AspectJProxyFactory factory = new AspectJProxyFactory(new TraineeServiceImpl());
    factory.addAspect(new AuthAspect(userService));
    traineeService = factory.getProxy();

    factory = new AspectJProxyFactory(new TrainerServiceImpl());
    factory.addAspect(new AuthAspect(userService));
    trainerService = factory.getProxy();

    factory = new AspectJProxyFactory(new TrainingServiceImpl());
    factory.addAspect(new AuthAspect(userService));
    trainingService = factory.getProxy();
  }

  @Test
  @DisplayName("TraineeService.get invokes AuthAspect.doAuth()")
  void testCase01() {
    assertThrows(AuthFailedException.class, () -> traineeService.get(credentials));
  }

  @Test
  @DisplayName("TraineeService.update invokes AuthAspect.doAuth()")
  void testCase02() {
    assertThrows(AuthFailedException.class, () -> traineeService.update(traineeDto));
  }

  @Test
  @DisplayName("TraineeService.deleteByUsername invokes AuthAspect.doAuth()")
  void testCase03() {
    assertThrows(AuthFailedException.class, () -> traineeService.deleteByUsername(credentials));
  }

  @Test
  @DisplayName("TrainerService.get invokes AuthAspect.doAuth()")
  void testCase04() {
    assertThrows(AuthFailedException.class, () -> trainerService.get(credentials));
  }

  @Test
  @DisplayName("TrainerService.get invokes AuthAspect.doAuth()")
  void testCase05() {
    assertThrows(AuthFailedException.class, () -> trainerService.update(trainerDto));
  }

  @Test
  @DisplayName("TrainerService.addTrainerToTrainee invokes AuthAspect.doAuth()")
  void testCase06() {
    assertThrows(AuthFailedException.class, () -> trainerService.addTrainerToTrainee(traineeDto ,"test"));
  }

  @Test
  @DisplayName("TrainerService.getTrainersNotAssignedToTrainee invokes AuthAspect.doAuth()")
  void testCase07() {
    assertThrows(AuthFailedException.class, () -> trainerService.getTrainersNotAssignedToTrainee(credentials));
  }

  @Test
  @DisplayName("TrainingService.getTraineeTrainingListByTrainerAndDateBetween invokes AuthAspect.doAuth()")
  void testCase08() {
    assertThrows(AuthFailedException.class, () -> trainingService.getTraineeTrainingListByTrainerAndDateBetween(credentials,
        List.of(), new Date(), new Date()));
  }

  @Test
  @DisplayName("TrainingService.getTrainerTrainingListByTraineeAndDateBetween invokes AuthAspect.doAuth()")
  void testCase09() {
    assertThrows(AuthFailedException.class, () -> trainingService.getTrainerTrainingListByTraineeAndDateBetween(credentials,
        List.of(), new Date(), new Date()));
  }
}