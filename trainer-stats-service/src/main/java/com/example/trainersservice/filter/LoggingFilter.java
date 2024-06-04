package com.example.trainersservice.filter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

@Slf4j
@Component
public class LoggingFilter extends OncePerRequestFilter {

  private static final String LOG_MESSAGE = """
      \040
      Endpoint: {},
      HTTP Method: {},
      Response:\040
        code:{}
        body:{}
      """;

  @Override
  protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request,
                                  HttpServletResponse response,
                                  jakarta.servlet.FilterChain filterChain) throws ServletException, IOException {
    MDC.put("transactionId", String.valueOf(UUID.randomUUID()));

    ContentCachingResponseWrapper responseCacheWrapperObject = new ContentCachingResponseWrapper(response);
    filterChain.doFilter(request,
        responseCacheWrapperObject); // If this statement is not executed then content is empty
    String responseStr = new String(responseCacheWrapperObject.getContentAsByteArray(), "UTF-8");
    responseCacheWrapperObject.copyBodyToResponse();

    log.info(
        LOG_MESSAGE,
        request.getRequestURI(),
        request.getMethod(),
        response.getStatus(),
        responseStr
    );
    MDC.clear();
  }
}