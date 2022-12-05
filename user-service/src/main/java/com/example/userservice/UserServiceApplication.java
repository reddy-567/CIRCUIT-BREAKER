package com.example.userservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@SpringBootApplication
@RestController
@RequestMapping("/user-service")
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}
	
	
	@Autowired
	@Lazy
	private RestTemplate restTemplate;
	
	
	private static final String url="http://localhost:9090/Orders";
	
	@GetMapping("/displayOrders")
	@CircuitBreaker(name="userService",fallbackMethod="fallback")
	public String getOrder()
	{
		//String url="http://8081/employee/message";
		return restTemplate.getForObject(url,String.class);
	}
	
	
	@Bean
	public RestTemplate resttemplate()
	{
		return new RestTemplate();
	}
	
	public String fallback(Exception e) {
		return "first-service is goes down";
	}

}
