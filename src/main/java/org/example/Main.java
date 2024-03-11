package org.example;


import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import org.example.configuration.AppConfig;
import org.example.dto.TraineeDto;
import org.example.dto.TrainerDto;
import org.example.dto.TrainingDto;
import org.example.entity.Training;
import org.example.service.TraineeService;
import org.example.service.TrainerService;
import org.example.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Main {
  public static void main(String[] args) {
    try (ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class)) {


//      TrainerDto dto1 = TrainerDto.builder()
//          .id(UUID.fromString("8c64b0bf-3e57-42d0-b28d-1dab7ac0ab56"))
//          .firstName("czxfadasd")
//          .lastName("xczcsadasd")
//          .isActive(false)
//          .build();

//      createTraineeFailsValidation(context);
//      createTraineePasses(context);
//      updateTraineeFailsAuth(context);
//      updateTraineeUpdates(context);
//      deleteTraineeDeletes(context);
//      createTrainer(context);
//      getTrainer(context);
//      getTrainee(context);
//      getTrainersNotAssignedToTrainee(context);
//      addTrainerToTrainee(context);
//      createTraining(context);
//      getTraineeTrainingListByTrainerAndDateBetween(context);

//      UserCredentialsDto credentials = UserCredentialsDto.builder()
//          .username("TeSt.tEsT116")
//          .password("_gF4nhyvN51RTg")
//          .build();
//
//      TraineeService traineeService = context.getBean(TraineeService.class);
//      TraineeDto traineeDto = traineeService.get(credentials);
////      traineeDto.setFirstName(null);
//      traineeDto.setPassword("_gF4nhyvN51RTg");
//      traineeDto.setLastName("");
//      traineeDto.setFirstName("");
//      traineeService.update(traineeDto);
//
//      TraineeDto trainee = traineeService.create(dto);
//      System.out.println(trainee.getId());
//      dto.setId(trainee.getId());
//      traineeService.update(dto);
//      traineeService.deleteById(dto.getId());
//
//          .createUser(UserDto.builder().firstName("test").lastName("test").username("test").isActive(true).build());
    }
  }

//  static TraineeDto traineeDto = TraineeDto.builder()
////          .id(UUID.fromString("d8c31766c3464e9360777d14cb71d8"))
//      .firstName("test1")
//      .lastName("test1")
//      .isActive(false)
//      .username("test.test121")
//      .birthDate(LocalDate.of(2005,12,12))
//      .address("address")
//      .password("4MYJR3HRRnFY4g")
//      .build();
//  static TrainerDto trainerDto = TrainerDto.builder()
//      .firstName("trainer")
//      .lastName("trainer")
//      .username("trainer.trainer1")
//      .specializations(List.of("type1", "type2"))
//      .password("yZUrLG7XaQe5Kg")
//      .build();
//
//  static TrainingDto trainingDto = TrainingDto.builder()
//      .trainingType("type1")
//      .name("name")
//      .date(LocalDate.of(2024,10,11))
//      .duration(3600L)
//      .build();
//
//  public static void createTraineeFailsValidation(ApplicationContext context){
//    TraineeService traineeService = context.getBean(TraineeService.class);
//    traineeDto.setLastName("");
//    traineeDto.setFirstName("");
//    traineeService.create(traineeDto);
//  }
//
//  public static void createTraineePasses(ApplicationContext context){
//    TraineeService traineeService = context.getBean(TraineeService.class);
//    traineeService.create(traineeDto);
//  }
//
//  public static void updateTraineeFailsAuth(ApplicationContext context){
//    TraineeService traineeService = context.getBean(TraineeService.class);
//    traineeDto.setPassword("random");
//    traineeService.update(traineeDto);
//  }
//
//  public static void updateTraineeUpdates(ApplicationContext context){
//    TraineeService traineeService = context.getBean(TraineeService.class);
//    traineeDto.setFirstName("new firstname");
//    traineeService.update(traineeDto);
//  }
//
//  public static void getTrainee(ApplicationContext context){
//    TraineeService traineeService = context.getBean(TraineeService.class);
//    traineeDto = traineeService.get(traineeDto);
//  }
//
//  public static void deleteTraineeDeletes(ApplicationContext context){
//    TraineeService traineeService = context.getBean(TraineeService.class);
//    traineeDto.setUsername("TeSt.tEsT113");
//    traineeDto.setPassword("random");
//    traineeService.deleteByUsername(traineeDto);
//  }
//
//  public static void createTrainer(ApplicationContext context){
//    TrainerService trainerService = context.getBean(TrainerService.class);
//
//    trainerService.create(trainerDto);
//  }
//
//  public static void getTrainer(ApplicationContext context){
//    TrainerService trainerService = context.getBean(TrainerService.class);
//
//    trainerDto = trainerService.get(trainerDto);
//    System.out.println(trainerDto);
//  }
//
//  public static void getTrainersNotAssignedToTrainee(ApplicationContext context){
//    TrainerService trainerService = context.getBean(TrainerService.class);
//    traineeDto.setPassword("4MYJR3HRRnFY4g");
//    System.out.println(trainerService.getTrainersNotAssignedToTrainee(traineeDto));
//  }
//
//  public static void addTrainerToTrainee(ApplicationContext context){
//    TrainerService trainerService = context.getBean(TrainerService.class);
//    traineeDto.setPassword("4MYJR3HRRnFY4g");
//    trainerService.addTrainerToTrainee(traineeDto, trainerDto.getUsername());
//  }
//
//  public static void createTraining(ApplicationContext context){
//    TrainingService trainerService = context.getBean(TrainingService.class);
//    trainingDto.setTraineeDto(traineeDto);
//    trainingDto.setTrainerDto(trainerDto);
//    trainerService.create(trainingDto);
//  }
//
//  public static void getTraineeTrainingListByTrainerAndDateBetween(ApplicationContext context){
//    TrainingService trainerService = context.getBean(TrainingService.class);
//    traineeDto.setPassword("4MYJR3HRRnFY4g");
//    System.out.println(trainerService.getTraineeTrainingListByTrainerAndDateBetween(
//        traineeDto,
//        List.of(trainerDto.getUsername()),
//        Date.from(LocalDate.of(2000,1,1).atStartOfDay(ZoneId.systemDefault()).toInstant()),
//        Date.from(LocalDate.of(2025,1,1).atStartOfDay(ZoneId.systemDefault()).toInstant())
//    ));
//  }

}