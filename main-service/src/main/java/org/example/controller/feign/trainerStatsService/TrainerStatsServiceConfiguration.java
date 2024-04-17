package org.example.controller.feign.trainerStatsService;

import feign.codec.ErrorDecoder;
import org.example.controller.feign.SimpleFeignErrorDecoder;
import org.springframework.context.annotation.Bean;

public class TrainerStatsServiceConfiguration {
  @Bean
  public ErrorDecoder errorDecoder() {
    return new SimpleFeignErrorDecoder("TrainerStatsService");
  }
}
