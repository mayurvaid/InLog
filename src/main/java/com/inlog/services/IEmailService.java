package com.inlog.services;

import java.util.List;

import com.inlog.entities.Email;

public interface IEmailService {
	void sendEmail(Email email);
	List<Email> getEmailList();
}
