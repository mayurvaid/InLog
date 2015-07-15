package com.inlog.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.inlog.commons.UserThread;
import com.inlog.services.IUserService;

@Component
public class WebInterceptor implements HandlerInterceptor {
	private static final String AUTH_HEADER = "X-AUTH-HEADER";
	private static final String AUTH_USER_HEADER = "X-AUTH-USER-HEADER";

	@Autowired
	private IUserService userService;

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object arg2) throws Exception {
		String authHeader = request.getHeader(AUTH_HEADER);
		String userName = request.getHeader(AUTH_USER_HEADER);
		if (!userService.validateAuthToken(userName, authHeader)) {
			throw new AccessDeniedException(
					"Token not found or token is not valid , please contact administrator");
		} else {
			UserThread.setThreadLocal(userName);
		}

		return true;
	}

}
