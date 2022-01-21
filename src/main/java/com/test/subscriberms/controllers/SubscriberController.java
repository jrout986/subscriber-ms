package com.test.subscriberms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.test.subscriberms.beans.User;
import com.test.subscriberms.clients.UserMsClient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@RestController
public class SubscriberController {
	@Autowired
	UserMsClient userClient;
	
	@GetMapping("/subscribers/{subId}")
	//@CircuitBreaker(name = "my-circuitbreaker", fallbackMethod = "fallback")
	@RateLimiter(name = "myRatelimit")
	public EntityModel<User> getSubsById(@PathVariable Integer subId) {
		EntityModel<User> userEntity=userClient.getUserByUserId(subId);
		//System.out.println(userEntity.getContent().getId());
		return userEntity;
	}
	
	public EntityModel<User> fallback(Exception ex){
		User user=new User();
		user.setName("This is a fallback user");
		EntityModel<User> model=EntityModel.of(user);
		return model;
	}
}
