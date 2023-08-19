package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;


public class JsonReadingApplication {
    public static void main(String[] args) throws InterruptedException {
        RateLimiterManager rateLimiterManager = new RateLimiterManager();

        String user1 = "user123";
        String user2 = "user456";

        for (int i = 0; i < 20; i++) {
            if (rateLimiterManager.makeApiCall(user1)) {
                System.out.println("User 1 - API call " + (i + 1) + " success");
            } else {
                System.out.println("User 1 - API call " + (i + 1) + " rate limited");
            }

            if (rateLimiterManager.makeApiCall(user2)) {
                System.out.println("User 2 - API call " + (i + 1) + " success");
            } else {
                System.out.println("User 2 - API call " + (i + 1) + " rate limited");
            }

            Thread.sleep(3000); // Simulate some processing time before the next API call
        }
    }
}
