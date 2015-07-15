package com.inlog.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.inlog.annotation.SecurityCheck;
import com.inlog.entities.InlogException;
import com.inlog.entities.User;
import com.inlog.enums.UserRoleEnum;
import com.inlog.services.IUserService;

@RestController
@RequestMapping("/api/v1")
public class UserController {
	private final IUserService userService;
	private static final Logger logger = LoggerFactory
			.getLogger(UserController.class);

	@Autowired
	public UserController(IUserService userService) {
		this.userService = userService;
	}

	@SecurityCheck(userRole = UserRoleEnum.ADMIN)
	@RequestMapping(value = "/addUserDetails", method = RequestMethod.POST, produces = "application/json")
	public User setUserDetails(@ModelAttribute("user") User user)
			throws InlogException {
		logger.info("Adding user details {}", user);
		
		userService.saveUserDetails(user);
		return user;
	}
	
	@RequestMapping(value = "/showUserDetails", method = RequestMethod.GET, produces = "application/json")
	public User showUserDetails(@ModelAttribute("user") User user) {
		return user;
	}

}
