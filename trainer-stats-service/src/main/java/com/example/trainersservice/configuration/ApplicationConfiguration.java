package com.example.trainersservice.configuration;

import com.example.trainersservice.dao.MongoTrainingSummaryDao;
import com.example.trainersservice.entity.TrainingMonth;
import com.example.trainersservice.entity.TrainingSummary;
import com.example.trainersservice.entity.TrainingYear;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class ApplicationConfiguration {
  @Value("${token.key}")
  private String jwtSigningKey;
  @Value("${service.main}")
  private String mainService;

  @Bean
  public List<TrainingSummary> storage() {
    return new CopyOnWriteArrayList<>();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecretKey getSigningKey() {
    byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  @Bean
  public LoadingCache<String, Integer> tokenCache() {
    return CacheBuilder.newBuilder()
        .expireAfterWrite(1, TimeUnit.MINUTES)
        .build(new CacheLoader<>() {
          @Override
          public Integer load(String token) {
            return 0;
          }
        });
  }

  @Bean
  public UserDetailsService userDetailsService() {
    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
    manager.createUser(User.withUsername(mainService).password("").build());
    return manager;
  }

  @Bean
  @Profile({"mongo", "integration"})
  public CommandLineRunner initData(MongoTrainingSummaryDao dao) {
    return _ -> {
      var testSummary = TrainingSummary.builder()
          .id(UUID.randomUUID())
          .firstname("trainer")
          .lastname("trainer")
          .username("trainer.trainer")
          .isActive(true)
          .years(List.of(
              TrainingYear.builder()
                  .year(2030)
                  .months(
                      List.of(
                          TrainingMonth.builder().month(1).summaryDuration(3600).build(),
                          TrainingMonth.builder().month(2).summaryDuration(3600).build(),
                          TrainingMonth.builder().month(3).summaryDuration(3600).build(),
                          TrainingMonth.builder().month(4).summaryDuration(3600).build()
                      )
                  )
                  .build(),
              TrainingYear.builder()
                  .year(2031)
                  .months(
                      List.of(
                          TrainingMonth.builder().month(1).summaryDuration(3600).build()
                      )
                  )
                  .build(),
              TrainingYear.builder()
                  .year(2032)
                  .months(
                      List.of(
                          TrainingMonth.builder().month(1).summaryDuration(3600).build()
                      )
                  )
                  .build(),
              TrainingYear.builder()
                  .year(2033)
                  .months(
                      List.of(
                          TrainingMonth.builder().month(1).summaryDuration(3600).build()
                      )
                  )
                  .build()

          ))
          .build();
      dao.getByTrainerUsername("trainer.trainer")
          .ifPresent(e -> testSummary.setId(e.getId()));
      dao.save(testSummary);
    };
  }
}
