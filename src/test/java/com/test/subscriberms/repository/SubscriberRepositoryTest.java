package com.test.subscriberms.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.test.subscriberms.beans.Subscriber;

@DataJpaTest
class SubscriberRepositoryTest {

	@Autowired
	TestEntityManager testEntityManager;
	
	@Autowired
	SubscriberRepository subRepo;
	
	@Test
	void testGetAllSubscribers() {
		//fail("Not yet implemented");
		Subscriber sub1=new Subscriber("John", 18, "Male");
		//sub1.setId(1);
		Subscriber sub2=new Subscriber("Mary", 25, "Female"); 
		Subscriber sub3=new Subscriber("Jack", 35, "Male"); 
		testEntityManager.persist(sub1);
		testEntityManager.persist(sub2); 
		testEntityManager.persist(sub3);

		testEntityManager.flush();
		List<Subscriber> subs=subRepo.findAll();
		assertEquals(subs.size(), 7);
	}

}
