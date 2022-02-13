package com.test.subscriberms.controllers;

import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.EntityModel;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.test.subscriberms.beans.User;
import com.test.subscriberms.clients.UserMsClient;
import com.test.subscriberms.repository.SubscriberRepository;

@WebMvcTest(controllers = SubscriberController.class)
class SubscriberControllerTest {
	@Autowired
	MockMvc mockmvc;
	
	@MockBean
	UserMsClient userClient;
	
	@MockBean
	SubscriberRepository subsRepo;
	
	@Test
	void testGetSubsById() throws Exception {
		User user1=new User();
		EntityModel<User> model=EntityModel.of(user1);
		Mockito.when(userClient.getUserByUserId(1)).thenReturn(model);
		mockmvc.perform(MockMvcRequestBuilders.get("/subscribers/1"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentType("application/hal+json"))
		.andExpect(MockMvcResultMatchers.content().json("{\"id\":null,\"name\":null,\"age\":null,\"gender\":null,\"port\":null}"));
		
		Mockito.verify(userClient, times(1)).getUserByUserId(1);
		//fail("Not yet implemented");
	}

}
