package com.inlog.config;

import org.joda.time.DateTime;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inlog.entities.AppError;

@ControllerAdvice
@Component
public class ExceptionHandling {

	@ResponseBody
	@ExceptionHandler(AccessDeniedException.class)
	public AppError handleCustomException(AccessDeniedException ex) {
		AppError accessErr = new AppError();

		accessErr.setErroCode("Access Denied");
		accessErr.setErrorMessage(ex.getMessage());
		accessErr.setCreatedAt(DateTime.now());

		return accessErr;

	}
	
	@ResponseBody
	@ExceptionHandler(Exception.class)
	public AppError handleException(Exception ex) {
		AppError appErr = new AppError();

		appErr.setErroCode(ex.getLocalizedMessage());
		appErr.setErrorMessage(ex.getMessage());
		appErr.setCreatedAt(DateTime.now());

		return appErr;

	}
	
	
}
