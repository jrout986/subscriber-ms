package com.test.subscriberms.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class TestController {
	private static final Logger LOGGER=LoggerFactory.getLogger(TestController.class);
	
	@LoadBalanced
	public static RestTemplate getTemplate() {
		return new RestTemplate();
	}
	
	@GetMapping("/retry")
	@Retry(name = "my-retry", fallbackMethod = "fallback")
	public String testRetry() {
		LOGGER.info("This is a retry logger");
		return getTemplate().getForEntity("http://randomurl:8080/testme", String.class, "").getBody();
	}
	
	@GetMapping("/circuitbreaker")
	@Retry(name = "my-circuitbreaker", fallbackMethod = "fallback")
	public String testCircuitBreaker() {
		LOGGER.info("This is a circuitbreaker logger");
		return getTemplate().getForEntity("http://randomurl:8080/testme", String.class, "").getBody();
	}
	
	@GetMapping("/bulkhead")
	@Bulkhead(name = "my-bulkhead")
	public String testBulkhead() {
		LOGGER.info("This is a bulkhead logger");
		return "This is a bulkhead return";
	}
	
	@GetMapping("/ratelimit")
	@RateLimiter(name = "myRatelimit")
	public String testRatelimit() {
		LOGGER.info("This is a ratelimit logger");
		return "This is a ratelimiter return";
		//return getTemplate().getForEntity("http://randomurl:8080/testme", String.class, "").getBody();
	}
	
	public String fallback(Exception ex) {
		return "This is a fallback call";
	}
}
