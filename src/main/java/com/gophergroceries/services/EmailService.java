package com.gophergroceries.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

	@Autowired
	private EmailAPI emailAPI;

	public boolean sendConfirmationEmail(String customerEmail) {
		boolean result = false;
		// TODO: Should be customer Email
		// String toAddr = customerEmail;
		// But for now ......
		logger.info("Would have sent to: " + customerEmail);
		String toAddr = "camroe@gmail.com";
		String fromAddr = "admin@gopher-groceries.com";

		// email subject
		String subject = "Gopher-Groceries Order Confirmation";

		// email body
		String body = "There you go.. You got an order confirmation email";
		logger.info("Sending Confirmation Email: "
				+ "\nTO:" + toAddr
				+ "\nFrom:" + fromAddr
				+ "\nSubjetct:" + subject);
		emailAPI.readyToSendEmail(toAddr, fromAddr, subject, body);
		result = true;
		return result;
	}
}
