package org.example.configuration.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class DaoFile {
  @Value("${data.file.trainee}")
  private String trainee;
  @Value("${data.file.trainer}")
  private String trainer;
  @Value("${data.file.training}")
  private String training;
  @Value("${data.file.user}")
  private String user;
}


