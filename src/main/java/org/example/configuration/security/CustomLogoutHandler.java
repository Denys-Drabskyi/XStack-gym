package org.example.configuration.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@RequiredArgsConstructor
public class CustomLogoutHandler implements LogoutHandler {
  private final LogoutBlacklist blacklist;
  @Override
  public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
    if (authentication != null && authentication.getDetails() != null) {
      try {
        String authorizationHeader = request.getHeader("Authorization");
        String token = "";
        if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
          token = authorizationHeader.substring(7);
        }

        blacklist.addToBlacklist(token);
        request.getSession().invalidate();
        System.out.println("User Successfully Logged Out");

      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
