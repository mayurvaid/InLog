package com.inlog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inlog.commons.UserThread;
import com.inlog.dao.repositories.EmailRepository;
import com.inlog.entities.Email;

@Service
public class EmailService implements IEmailService {
	private final EmailRepository emailRepository;

	@Autowired
	public EmailService(EmailRepository emailRepository) {
		this.emailRepository = emailRepository;
	}

	@Override
	public void sendEmail(Email email) {
		emailRepository.save(email);
	}

	@Override
	public List<Email> getEmailList() {
		return emailRepository.getEmailByToEmail(UserThread.getUserName());
	}

}
