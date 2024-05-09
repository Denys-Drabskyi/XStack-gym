package com.example.trainersservice.security;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.google.common.cache.LoadingCache;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TokenBlacklistTest {

  @Mock
  private LoadingCache<String, Integer> tokenCache;

  @InjectMocks
  private TokenBlacklist tokenBlacklist;

  @BeforeEach
  public void setUp() {
    when(tokenCache.getUnchecked("blacklistedToken")).thenReturn(1);
    when(tokenCache.getUnchecked("nonBlacklistedToken")).thenReturn(0);
  }

  @Test
  public void testIsBlacklisted() {
    assertTrue(tokenBlacklist.isBlacklisted("blacklistedToken"));
    assertFalse(tokenBlacklist.isBlacklisted("nonBlacklistedToken"));
  }
}
