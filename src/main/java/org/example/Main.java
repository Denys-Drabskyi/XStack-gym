package org.example;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
@Component
public class Main {

  // TODO: 3/19/2024 переглянути на свіжу голову ентіті
  @SneakyThrows
  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);
  }

}