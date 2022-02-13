package com.test.subscriberms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.subscriberms.beans.Subscriber;

public interface SubscriberRepository extends JpaRepository<Subscriber, Integer>{

}
