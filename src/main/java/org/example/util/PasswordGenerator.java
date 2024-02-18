package org.example.util;

import java.security.SecureRandom;
import java.util.Base64;

public class PasswordGenerator {

  public String generatePassword(){
    return generateValue(10);
  }

  public String generateValue(int byteLength) {
    SecureRandom secureRandom = new SecureRandom();
    byte[] token = new byte[byteLength];
    secureRandom.nextBytes(token);
    return Base64.getUrlEncoder().withoutPadding().encodeToString(token);
  }
}
