package com.inlog.annotation;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

import com.inlog.commons.UserThread;
import com.inlog.entities.User;
import com.inlog.services.IUserService;

/**
 * To intercept any security check call
 * Any method annotated with {@link SecurityCheck} get intercepted by this aspect 
 * @author MAYUR
 *
 */
@Component
@Aspect
public class SecurityAspect {
	private final IUserService userService;

	@Autowired
	public SecurityAspect(IUserService userService) {
		this.userService = userService;
	}

	@Before("@annotation(securityCheck)")
	public void publicMethodInsideAClassMarkedWithAtMonitor(
			SecurityCheck securityCheck) {
		User user = userService.getUserDetails(UserThread.getUserName());

		if (!StringUtils.equalsIgnoreCase(user.getUserRole(), securityCheck
				.userRole().name())) {
			throw new AccessDeniedException(
					"You are not allowed to create user !!! Please login as admin");
		}
	}
}
