package com.test.subscriberms.configs;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.test.subscriberms.beans.ExceptionFormat;
import com.test.subscriberms.exceptions.SubscriberNotFoundException;

@RestController
@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception {
		ExceptionFormat exFormat=new ExceptionFormat(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(exFormat, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(SubscriberNotFoundException.class)
	public final ResponseEntity<Object> handleSubscriberNotFoundException(Exception ex, WebRequest request) throws Exception {
		ExceptionFormat exFormat=new ExceptionFormat(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(exFormat, HttpStatus.NOT_FOUND);
	}
	
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionFormat exFormat=new ExceptionFormat(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(exFormat, HttpStatus.BAD_REQUEST);
	}
}
