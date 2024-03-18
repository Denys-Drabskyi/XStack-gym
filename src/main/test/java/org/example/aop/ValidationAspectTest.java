package org.example.aop;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.example.TestConfig;
import org.example.dto.TraineeDto;
import org.example.dto.TrainerDto;
import org.example.dto.TrainingDto;
import org.example.dto.UserCredentialsDto;
import org.example.service.TraineeService;
import org.example.service.TrainerService;
import org.example.service.TrainingService;
import org.example.service.UserService;
import org.example.service.impl.TraineeServiceImpl;
import org.example.service.impl.TrainerServiceImpl;
import org.example.service.impl.TrainingServiceImpl;
import org.example.service.impl.UserServiceImpl;
import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

class ValidationAspectTest {
  private final UserCredentialsDto credentials = UserCredentialsDto.builder().build();
  private final TraineeDto traineeDto = TraineeDto.builder().build();
  private final TrainerDto trainerDto = TrainerDto.builder().build();
  private final TrainingDto trainingDto = TrainingDto.builder().build();

  private static TraineeService traineeService;
  private static TrainerService trainerService;
  private static TrainingService trainingService;

  @BeforeAll
  static void setUp() {
    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    ValidationAspect aspect = new ValidationAspect(validator);

    AspectJProxyFactory factory = new AspectJProxyFactory(new TraineeServiceImpl());
    factory.addAspect(aspect);
    traineeService = factory.getProxy();

    factory = new AspectJProxyFactory(new TrainerServiceImpl());
    factory.addAspect(aspect);
    trainerService = factory.getProxy();

    factory = new AspectJProxyFactory(new TrainingServiceImpl());
    factory.addAspect(aspect);
    trainingService = factory.getProxy();
  }

  @Test
  @DisplayName("TraineeService.get invokes ValidationAspect")
  void testCase01() {
    assertThrows(ConstraintViolationException.class, () -> traineeService.get(credentials));
  }

  @Test
  @DisplayName("TraineeService.create invokes ValidationAspect")
  void testCase02() {
    assertThrows(ConstraintViolationException.class, () -> traineeService.create(traineeDto));
  }

  @Test
  @DisplayName("TraineeService.update invokes ValidationAspect")
  void testCase03() {
    assertThrows(ConstraintViolationException.class, () -> traineeService.update(traineeDto));
  }

  @Test
  @DisplayName("TraineeService.deleteByUsername invokes ValidationAspect")
  void testCase04() {
    assertThrows(ConstraintViolationException.class, () -> traineeService.deleteByUsername(credentials));
  }

  @Test
  @DisplayName("TrainerService.get invokes ValidationAspect")
  void testCase05() {
    assertThrows(ConstraintViolationException.class, () -> trainerService.get(credentials));
  }

  @Test
  @DisplayName("TrainerService.create invokes ValidationAspect")
  void testCase06() {
    assertThrows(ConstraintViolationException.class, () -> trainerService.create(trainerDto));
  }

  @Test
  @DisplayName("TrainerService.update invokes ValidationAspect")
  void testCase07() {
    assertThrows(ConstraintViolationException.class, () -> trainerService.update(trainerDto));
  }

  @Test
  @DisplayName("TrainerService.addTrainerToTrainee invokes ValidationAspect")
  void testCase08() {
    assertThrows(ConstraintViolationException.class, () -> trainerService.addTrainerToTrainee(credentials, ""));
  }

  @Test
  @DisplayName("TrainerService.getTrainersNotAssignedToTrainee invokes ValidationAspect")
  void testCase09() {
    assertThrows(ConstraintViolationException.class, () -> trainerService.getTrainersNotAssignedToTrainee(credentials));
  }


  @Test
  @DisplayName("TrainingService.create invokes ValidationAspect")
  void testCase10() {
    assertThrows(ConstraintViolationException.class, () -> trainingService.create(trainingDto));
  }

  @Test
  @DisplayName("TrainingService.getTraineeTrainingListByTrainerAndDateBetween invokes ValidationAspect")
  void testCase11() {
    assertThrows(ConstraintViolationException.class,
        () -> trainingService.getTraineeTrainingListByTrainerAndDateBetween(credentials, List.of(), new Date(), new Date()));
  }

  @Test
  @DisplayName("TrainingService.getTrainerTrainingListByTraineeAndDateBetween invokes ValidationAspect")
  void testCase12() {
    assertThrows(ConstraintViolationException.class,
        () -> trainingService.getTrainerTrainingListByTraineeAndDateBetween(credentials, List.of(), new Date(), new Date()));
  }
}