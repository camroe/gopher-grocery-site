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

	public boolean sendConfirmationEmail(String customerEmail, String confirmationId, String hostname) {
		boolean result = false;
		// TODO: Need to expand on the Customer Email message and make it nice.
		// String toAddr = customerEmail;
		// But for now ......
		logger.info("Would have sent to: " + customerEmail);
		String toAddr = "camroe@gmail.com";
		String fromAddr = "admin@gopher-groceries.com";

		// email subject
		String subject = "Gopher-Groceries Order Confirmation - " + confirmationId;

		// email body
		StringBuilder body = new StringBuilder(300);
		body.append("Thank you for your order (")
				.append(confirmationId)
				.append(") from Gopher-Groceries.")
				.append("\n")
				.append("We are reviewing your order and will be in contact shortly");

		body.append("\n")
				.append(
						"Click on the following link or cut/paste the URL to your browser address bar to see your confirmed order.")
				.append("\n");
		body.append("http://")
				.append(hostname)
				.append("/v1/customerconfirmedorders/")
				.append(confirmationId);

		logger.info("Sending Confirmation Email: "
				+ "\nTO:" + toAddr
				+ "\nFrom:" + fromAddr
				+ "\nSubjetct:" + subject);
		emailAPI.readyToSendEmail(toAddr, fromAddr, subject, body.toString());
		result = true;
		return result;
	}
}
