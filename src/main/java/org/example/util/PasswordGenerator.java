package org.example.util;

import java.security.SecureRandom;
import java.util.Base64;

public class PasswordGenerator {

  private static final SecureRandom RANDOM = new SecureRandom();

  public static String generatePassword(){
    return generateValue(10);
  }

  public static String generateValue(int byteLength) {
    byte[] token = new byte[byteLength];
    RANDOM.nextBytes(token);
    return Base64.getUrlEncoder().withoutPadding().encodeToString(token);
  }
}
