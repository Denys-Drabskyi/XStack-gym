package org.example.security;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationFailureListener implements
    ApplicationListener<AuthenticationFailureBadCredentialsEvent> {
  private final HttpServletRequest request;
  private final BruteForceBlacklist blacklist;

  @Override
  public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent e) {
    final String xfHeader = request.getHeader("X-Forwarded-For");
    if (xfHeader == null || xfHeader.isEmpty() || !xfHeader.contains(request.getRemoteAddr())) {
      blacklist.loginFailed(request.getRemoteAddr());
    } else {
      blacklist.loginFailed(xfHeader.split(",")[0]);
    }
  }
}
