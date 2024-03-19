package org.example.interceptor;

import java.io.IOException;
import java.util.UUID;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

@Slf4j
@Component
@WebFilter(filterName = "LoggingFilter", urlPatterns = "/*")
public class LoggingFilter extends OncePerRequestFilter {

  private static final String LOG_MESSAGE = """
          \040
          Endpoint: {},
          HTTP Method: {},
          Response:\040
            code:{}
            body:{}
          """;

//  @Override
//  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//    String transactionId = UUID.randomUUID().toString();
//    MDC.put("transactionId", transactionId);
//    return HandlerInterceptor.super.preHandle(request, response, handler);
//  }
//
//  @Override
//  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
//      throws Exception {
//    log.info(
//        LOG_MESSAGE,
//        request.getRequestURI(),
//        request.getMethod(),
//        response.getStatus(),
//        getResponseBody(response)
//    );
//    MDC.clear();
//    HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
//  }

  private String getResponseBody(HttpServletResponse response){
    ContentCachingResponseWrapper responseCacheWrapperObject = new ContentCachingResponseWrapper((HttpServletResponse) response);
    try {
      byte[] responseArray = responseCacheWrapperObject.getContentAsByteArray();
      String responseStr = new String(responseArray, responseCacheWrapperObject.getCharacterEncoding());
      //....use responsestr to make the signature

      responseCacheWrapperObject.copyBodyToResponse();

      return responseStr;
    } catch (Exception ignored){

    }

    return null;
  }



  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    MDC.put("transactionId", String.valueOf(UUID.randomUUID()));

    ContentCachingResponseWrapper responseCacheWrapperObject = new ContentCachingResponseWrapper(response);
    filterChain.doFilter(request, responseCacheWrapperObject); // If this statement is not executed then content is empty
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