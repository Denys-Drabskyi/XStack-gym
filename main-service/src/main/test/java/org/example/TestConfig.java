package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Primary
@Configuration
@EnableJpaRepositories(basePackages = "org.example.repository")
public class TestConfig {
  @Bean
  public ObjectMapper objectMapper() {
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    return mapper;
  }

//  @Bean
//  public MockMvc mockMvc(WebApplicationContext wac) {
//    return MockMvcBuilders.webAppContextSetup(wac)
//        .addFilter(
//            new CharacterEncodingFilter("UTF-8", true))
//        .build();
//  }
}
