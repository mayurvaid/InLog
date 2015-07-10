package com.inlog.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.inlog.entities.User;
import com.inlog.services.IUserService;

@RestController
@RequestMapping("/api/v1")
public class UserController {
	private final IUserService userService;
	private static final Logger logger = LoggerFactory
			.getLogger(HelloController.class);

	@Autowired
	public UserController(IUserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/addUserDetails", method = RequestMethod.POST, produces = "application/json")
	public User setUserDetails(@ModelAttribute("user") User user) {
		logger.info("Adding user details {}", user);
		
		userService.saveUserDetails(user);
		return user;
	}
	
	@RequestMapping(value = "/showUserDetails", method = RequestMethod.GET, produces = "application/json")
	public User showUserDetails(@ModelAttribute("user") User user) {
		return user;
	}

}
