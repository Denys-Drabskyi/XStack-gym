package com.example.trainersservice.security;

import com.google.common.cache.LoadingCache;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenBlacklist {
  private final LoadingCache<String, Integer> tokenCache;

  public void addToBlacklist(String token) {
    int attempts = tokenCache.getUnchecked(token) + 1;
    tokenCache.put(token, attempts);
  }

  public boolean isBlacklisted(String token) {
    return tokenCache.getUnchecked(token) == 1;
  }
}
