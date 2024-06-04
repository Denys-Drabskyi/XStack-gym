package com.example;

import java.util.Map;
import java.util.HashMap;
import lombok.Getter;
import lombok.Setter;
import org.apache.http.HttpResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

@Getter
@Setter
public class Storage {
  private static Storage instance;

  private Map<String, String> storage;
  public ObjectMapper mapper;
  public Object body;
  public HttpResponse rez;

  private Storage() {
    storage = new HashMap<>();
    mapper = new ObjectMapper();
  }

  public static synchronized Storage getInstance() {
    if (instance == null) {
      instance = new Storage();
    }
    return instance;
  }

  public void put(String key, String value) {
    storage.put(key, value);
  }

  public String get(String key) {
    return storage.get(key);
  }
}

