package com.example;

import java.util.Map;
import java.util.HashMap;

public class Storage {
  private static Storage instance;

  private Map<String, String> storage;

  private Storage() {
    storage = new HashMap<>();
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

