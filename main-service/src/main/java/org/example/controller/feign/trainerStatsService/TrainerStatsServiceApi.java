package org.example.controller.feign.trainerStatsService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.example.dto.RegisterTrainingEventDto;
import org.example.dto.TrainerStatisticsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@Profile("rest")
@FeignClient(name = "${service.trainer-stats-service}", configuration = TrainerStatsServiceConfiguration.class)
public interface TrainerStatsServiceApi {
  @GetMapping("/trainer/{username}")
  @CircuitBreaker(name = "TrainerStatsServiceCircuitBreaker")
  TrainerStatisticsDto stats(@PathVariable String username);

  @PostMapping("/trainer")
  @CircuitBreaker(name = "TrainerStatsServiceCircuitBreaker")
  void registerTrainingEvent(RegisterTrainingEventDto registerTrainingEventDto);
}
