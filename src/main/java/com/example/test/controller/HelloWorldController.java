package com.example.test.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@GetMapping(value = {"/api/employees/{name}","/api/employees"})
	public String helloWorld(@PathVariable(value="name",required= false) Optional<String> empName) {

		if (empName.isPresent() || empName.equals("runtime") ) {
			{
				return empName.get();
				//throw new RuntimeException("Hey Customer, We are busy, please come back later");
			}
		} else {
			return "ID: Default Employee";
		}

	}
	
	@GetMapping(value = {"/api/emp/{name}"})
	public String helloWorld2(@PathVariable(value="name",required= false) String empName) {

		if (empName.equals("runtime") ) {
			{
				throw new RuntimeException("Hey Customer, We are busy, please come back later");
			}
		} else {
			return "ID: Default Employee";
		}

	}
}
