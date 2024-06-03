package org.example.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MvcResult;

@Component
@RequiredArgsConstructor
public class StepsContainer {
  public MvcResult rez;
  public Object body;
  public String username;
  public String password;
}
