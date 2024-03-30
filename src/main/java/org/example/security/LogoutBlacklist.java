package org.example.security;

import com.google.common.cache.LoadingCache;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LogoutBlacklist {
  private final LoadingCache<String, Integer> logoutCache;
  public void addToBlacklist(String token) {
    int attempts = logoutCache.getUnchecked(token) + 1;
    logoutCache.put(token, attempts);
  }

  public boolean isBlacklisted(String token) {
    return logoutCache.getUnchecked(token) == 1;
  }
}
