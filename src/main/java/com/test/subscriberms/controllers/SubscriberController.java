package com.test.subscriberms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.test.subscriberms.beans.User;
import com.test.subscriberms.clients.UserMsClient;

@RestController
public class SubscriberController {
	@Autowired
	UserMsClient userClient;
	
	@GetMapping("/subscribers/{subId}")
	public EntityModel<User> getSubsById(@PathVariable Integer subId) {
		EntityModel<User> userEntity=userClient.getUserByUserId(subId);
		//System.out.println(userEntity.getContent().getId());
		return userEntity;
	}
}
