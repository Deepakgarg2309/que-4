package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class JsonReadingApplication {
	public static void main(String[] args) throws InterruptedException {
        // Create a rate limiter with a capacity of 15 tokens and a refill interval of 4 seconds
        RateLimiter rateLimiter = new RateLimiter(15, 4000);

        for (int i = 0; i < 20; i++) {
            if (rateLimiter.makeApiCall()) {
                System.out.println("API call " + (i + 1) + " success");
            } else {
                System.out.println("API call " + (i + 1) + " rate limited");
            }
            Thread.sleep(3000); // Simulate some processing time before the next API call
        }
    }

}
