package com.inlog.config;

import org.springframework.data.domain.AuditorAware;

public class UserNameAuditor implements AuditorAware<String> {
	private static final String USER_NAME = "InLog";

	@Override
	public String getCurrentAuditor() {
		return USER_NAME;
	}

}
