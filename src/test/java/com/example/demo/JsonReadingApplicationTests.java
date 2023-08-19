package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JsonReadingApplicationTests {
	private RateLimiterManager rateLimiterManager;

	@BeforeEach
	public void setUp() {
		rateLimiterManager = new RateLimiterManager();
	}

	@Test
	public void testRateLimiterManager() {
		String userId = "user1";
		String userId2 = "user2";
		int apiCallCount = 8;

		for (int i = 0; i < apiCallCount; i++) {
			assertTrue(rateLimiterManager.makeApiCall(userId));
			assertTrue(rateLimiterManager.makeApiCall(userId2));

		}

		assertFalse(rateLimiterManager.makeApiCall(userId));
	}
}
