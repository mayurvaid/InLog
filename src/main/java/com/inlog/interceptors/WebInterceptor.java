package com.inlog.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.inlog.services.IUserService;

@Component
public class WebInterceptor implements HandlerInterceptor {
	private static final String AUTH_HEADER = "X-AUTH-HEADER";
	private static final String ADD_USER_REQUEST = "/InLog/api/v1/addUserDetails";
	private static final String USER_NAME = "username";

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
		if (StringUtils.equalsIgnoreCase(ADD_USER_REQUEST,
				request.getRequestURI())) {
			return true;
		}

		String authHeader = request.getHeader(AUTH_HEADER);
		String userName = request.getParameter(USER_NAME);
		if (!userService.validateAuthToken(userName, authHeader)) {
			throw new AccessDeniedException(
					"Token not found or token is not valid , please contact administrator");
		}
		return true;
	}

}
