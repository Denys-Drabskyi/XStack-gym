package org.example;


import org.example.configuration.AppConfig;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
  public static void main(String[] args) {
    try (ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class)) {

    }
  }
}