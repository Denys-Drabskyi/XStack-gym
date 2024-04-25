package org.example.controller.feign.trainerStatsService;

import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import org.example.controller.feign.SimpleFeignErrorDecoder;
import org.example.service.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class TrainerStatsServiceConfiguration {
  private final JwtService jwtService;

  @Bean
  public ErrorDecoder errorDecoder() {
    return new SimpleFeignErrorDecoder("TrainerStatsService");
  }

  @Bean
  public RequestInterceptor requestInterceptor() {
    return template -> {
      var accessToken = jwtService.generateServiceToken();
      template.header("Authorization", "Bearer " + accessToken);
    };
  }
}