package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Primary
@Configuration
@ComponentScan(basePackages = "org.example")
@PropertySource("classpath:application-test.properties")
@EnableJpaRepositories(basePackages = "org.example.repository")
@EnableTransactionManagement
public class TestConfig {
  @Bean
  public ObjectMapper objectMapper(){
    return new ObjectMapper();
  }

  @Bean
  public ValidatorFactory validatorFactory() {
    return Validation.buildDefaultValidatorFactory();
  }
}
