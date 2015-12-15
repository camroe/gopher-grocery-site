package com.gophergroceries.services;

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

	/**
	 * This method moves the cart to the confirmed orders table and encrypts the
	 * confirmation id.
	 * 
	 * @param methodOfPayment
	 *          is used to set the method of payment in the confirmed orders
	 *          table.
	 * @param gopherCookie
	 *          is used to get the cart that is being moved to the confirmed
	 *          orders table.
	 * @return String - the ConfirmationID of the ordered cart. (not encrypted).
	 */
	public String transferOrderToSubmitted(String methodOfPayment, GopherCookie gopherCookie) {
		String confirmationID = DeliveryService.FAILED_CONFIRMATION;

		OrdersEntity oe = null;
		oe = orderService.getOrderWithCartKey(gopherCookie.getCookieValue());
		ConfirmedOrdersEntity coe = moveToConfirmed(oe, methodOfPayment);
		confirmedOrdersRepository.saveAndFlush(coe);
		confirmationID = coe.getConfirmationID();
		ordersRepository.delete(oe.getId());
		// TODO: Put email on a queue.
		emailService.sendConfirmationEmail(coe.getEmail(), coe.getConfirmationID());
		return confirmationID;
	}

	private ConfirmedOrdersEntity moveToConfirmed(OrdersEntity oe, String methodOfPayment) {
		return ConfirmedOrdersEnityFactory.createBasedOn(oe, methodOfPayment);
	}
}
