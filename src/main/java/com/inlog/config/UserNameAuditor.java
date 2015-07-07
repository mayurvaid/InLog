package com.inlog.config;

import org.springframework.data.domain.AuditorAware;

public class UserNameAuditor implements AuditorAware<String> {

	@Override
	public String getCurrentAuditor() {
		return "Mayur";
	}

}
