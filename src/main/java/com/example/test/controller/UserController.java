package com.example.test.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.test.dal.UserDAL;
import com.example.test.dal.UserRepository;
import com.example.test.exception.ResourceNotFoundException;
import com.example.test.model.User;


//https://zetcode.com/springboot/controlleradvice/

@RestController
@RequestMapping(value = "/")
public class UserController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private final UserRepository userRepository;

	private final UserDAL userDAL;

	public UserController(UserRepository userRepository, UserDAL userDAL) {
		this.userRepository = userRepository;
		this.userDAL = userDAL;
	}

	@GetMapping(value = { "/runtime" })
	public String runtimeException() {

		throw new RuntimeException("Hey Customer, We are busy, please come back later");

	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public User addNewUsers(@RequestBody User user) {
		log.info("Saving user.");
		return userRepository.save(user);
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<User> getAllUsers() {
		log.info("Getting all users.");
		return userRepository.findAll();
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public User getUser(@PathVariable String userId)  throws ResourceNotFoundException{
		log.info("Getting user with ID: {}.", userId);
		return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found"));
		
	}


	@RequestMapping(value = "/settings/{userId}", method = RequestMethod.GET)
	public Object getAllUserSettings(@PathVariable String userId) throws ResourceNotFoundException, Exception {

		User user = userRepository.findById(userId).get();

		
			return userDAL.getAllUserSettings(userId);

		
	}

	// @RequestMapping(value = "/settings/{userId}/{key}", method =
	// RequestMethod.GET)
	// public String getUserSetting(@PathVariable String userId, @PathVariable
	// String key) {
	// //User user = userRepository.findOne(userId);
	// String setting = userDAL.getUserSetting(userId, key);
	// LOG.info("Setting = "+setting);
	// if (setting != null) {
	// return setting;
	// } else {
	// return "Setting not found.";
	// }
	// }

	@RequestMapping(value = "/settings/{userId}/{key}", method = RequestMethod.GET)
	public String getUserSetting(@PathVariable String userId, @PathVariable String key) {
		return userDAL.getUserSetting(userId, key);
	}

	@RequestMapping(value = "/settings/{userId}/{key}/{value}", method = RequestMethod.GET)
	public String addUserSetting(@PathVariable String userId, @PathVariable String key, @PathVariable String value) {
		// User user = userRepository.findOne(userId);

		User user = userRepository.findById(userId).get();
		if (user != null) {
			user.getUserSettings().put(key, value);
			userRepository.save(user);
			return "Key added";
		} else {
			return "User not found.";
		}
	}
}