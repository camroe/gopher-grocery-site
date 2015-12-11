package com.gophergroceries.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class EmailAPI {

	private static final Logger logger = LoggerFactory.getLogger(EmailAPI.class);

	@Autowired
	private MailSender mailSender;

	public void readyToSendEmail(String toAddress, String fromAddress, String subject, String msgBody) {

		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom(fromAddress);
		msg.setTo(toAddress);
		msg.setSubject(subject);
		msg.setText(msgBody);

		try {
			mailSender.send(msg);
		} catch (MailException e) {
			logger.error("Error send message to " + toAddress + ". Subject is : " + subject);
			e.printStackTrace();
		}
	}

}
