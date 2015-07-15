package com.inlog.commons;

import org.springframework.data.domain.AuditorAware;

public class UserNameAuditor implements AuditorAware<String> {

	@Override
	public String getCurrentAuditor() {
		return UserThread.getUserName();
	}

}
