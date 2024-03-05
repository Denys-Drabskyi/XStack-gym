package org.example;


import org.example.configuration.AppConfig;
import org.example.dto.TraineeDto;
import org.example.dto.UserCredentialsDto;
import org.example.service.TraineeService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Main {
  public static void main(String[] args) {
    try (ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class)) {

    }
  }
}