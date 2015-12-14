package com.gophergroceries.services;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gophergroceries.cookies.GopherCookie;
import com.gophergroceries.model.entities.ConfirmedOrdersEnityFactory;
import com.gophergroceries.model.entities.ConfirmedOrdersEntity;
import com.gophergroceries.model.entities.OrdersEntity;
import com.gophergroceries.model.repository.ConfirmedOrdersRepository;
import com.gophergroceries.model.repository.OrdersRepository;
import com.gophergroceries.results.OrderSummaryResult;

@Service
public class DeliveryService {

	private static final Logger logger = LoggerFactory.getLogger(DeliveryService.class);

	public static final String FAILED_CONFIRMATION = "Failed";

	@Autowired
	private OrdersRepository ordersRepository;

	@Autowired
	private ConfirmedOrdersRepository confirmedOrdersRepository;

	@Autowired
	private OrderService orderService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private EncryptionDecryptionService edService;

	public OrderSummaryResult setDeliveryInformation(
			String firstName,
			String lastName,
			String location,
			String unit,
			String phone,
			String email,
			String checkinDate,
			String comment,
			GopherCookie gopherCookie) {
		// TODO: For now assume all is valid.
		OrderSummaryResult osr = orderService.getOrderSummary(gopherCookie);
		OrdersEntity oe = osr.getOrderSummary().getOrder().getOrderEntity();
		oe.setCity("Salt Lake City");
		oe.setZipCode("84121");
		oe.setState("Ut");
		oe.setFirstName(firstName);
		oe.setLastName(lastName);
		oe.setLocation(location);
		oe.setUnit(unit);
		oe.setPhone(phone);
		oe.setEmail(email);
		oe.setCheckinDate(checkinDate);
		oe.setComment(comment);
		ordersRepository.saveAndFlush(oe);
		osr.setError(false);
		osr.setErrorMsg("Delivery Set");
		logger.trace("DeliveryService called");
		return osr;
	}

	public String transferOrderToSubmitted(String methodOfPayment, GopherCookie gopherCookie) {
		String confirmationID = DeliveryService.FAILED_CONFIRMATION;

		OrdersEntity oe = null;
		oe = orderService.getOrderWithCartKey(gopherCookie.getCookieValue());
		ConfirmedOrdersEntity coe = moveToConfirmed(oe, methodOfPayment);
		confirmedOrdersRepository.saveAndFlush(coe);
		confirmationID = coe.getConfirmationID();
		ordersRepository.delete(oe.getId());
		String testEncryption = "";
		try {
			testEncryption = edService.encrypt(coe.getSessionID());
		} catch (InvalidKeyException | UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException
				| InvalidAlgorithmParameterException | ShortBufferException | IllegalBlockSizeException
				| BadPaddingException e) {
			logger.error("Encryption Error: " + coe.getSessionID());
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info(testEncryption);
		emailService.sendConfirmationEmail(coe.getEmail());
		return confirmationID;
	}

	private ConfirmedOrdersEntity moveToConfirmed(OrdersEntity oe, String methodOfPayment) {
		return ConfirmedOrdersEnityFactory.createBasedOn(oe, methodOfPayment);
	}
}
