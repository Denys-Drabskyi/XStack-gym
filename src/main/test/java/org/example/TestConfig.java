package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Primary
@Configuration
@EnableJpaRepositories(basePackages = "org.example.repository")
public class TestConfig {
  @Bean
  public ObjectMapper objectMapper(){
    return new ObjectMapper();
  }

//  @Bean
//  public ValidatorFactory validatorFactory() {
//    return Validation.buildDefaultValidatorFactory();
//  }
}
