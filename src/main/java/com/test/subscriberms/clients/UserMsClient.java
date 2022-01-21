package com.test.subscriberms.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.test.subscriberms.beans.User;


@FeignClient("api-gateway/my-users")
public interface UserMsClient {
	
	@GetMapping("/users/{userId}")
	public EntityModel<User> getUserByUserId(@PathVariable Integer userId);
}
