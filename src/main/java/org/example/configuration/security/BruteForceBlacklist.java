package org.example.configuration.security;

import com.google.common.cache.LoadingCache;
import jakarta.servlet.http.HttpServletRequest;
import java.util.concurrent.ExecutionException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BruteForceBlacklist {
  public static final int MAX_ATTEMPT = 10;
  private final LoadingCache<String, Integer> bruteForceCache;

  public void loginFailed(final String ip) {
    int attempts;
    try {
      attempts = bruteForceCache.get(ip);
    } catch (final ExecutionException e) {
      attempts = 0;
    }
    attempts++;
    bruteForceCache.put(ip, attempts);
  }

  public boolean isBlocked(HttpServletRequest request) {
    try {
      return bruteForceCache.get(getClientIP(request)) >= MAX_ATTEMPT;
    } catch (final ExecutionException e) {
      return false;
    }
  }

  private String getClientIP(HttpServletRequest request) {
    final String xfHeader = request.getHeader("X-Forwarded-For");
    if (xfHeader != null) {
      return xfHeader.split(",")[0];
    }
    return request.getRemoteAddr();
  }
}
