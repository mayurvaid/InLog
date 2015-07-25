package com.inlog.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inlog.entities.Email;
import com.inlog.services.IEmailService;

@RestController
@RequestMapping("/api/v1")
public class EmailController {
	private final IEmailService emailService;

	@Autowired
	public EmailController(IEmailService emailService) {
		this.emailService = emailService;
	}

	@RequestMapping("/sendEmail")
	public Email sendEmail(@ModelAttribute("email") Email email) {
		emailService.sendEmail(email);
		
		return email;
	}

	@RequestMapping("/getMails")
	public List<Email> getMails() {
		return emailService.getEmailList();
	}

}
