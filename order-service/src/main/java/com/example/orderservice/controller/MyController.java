package com.example.orderservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
	
	//@Autowired
	//private OrderRepo repo;
	
	@GetMapping("/Orders")
	public String getAll()
	{
		return  "all orders";
	}

}
