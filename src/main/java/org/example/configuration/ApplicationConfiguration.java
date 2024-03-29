package org.example.configuration;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.concurrent.TimeUnit;

@Configuration
public class ApplicationConfiguration {
  @Value("${token.key}")
  private String jwtSigningKey;

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
  public LoadingCache<String, Integer> logoutCache() {
    return CacheBuilder.newBuilder()
        .expireAfterWrite(1, TimeUnit.DAYS)
        .build(new CacheLoader<>() {
          @Override
          public Integer load(String token) {
            return 0;
          }
        });
  }

  @Bean
  public LoadingCache<String, Integer> bruteForceCache() {
    return CacheBuilder.newBuilder()
        .expireAfterWrite(30, TimeUnit.MINUTES)
        .build(new CacheLoader<>() {
          @Override
          public Integer load(String token) {
            return 0;
          }
        });
  }
}
