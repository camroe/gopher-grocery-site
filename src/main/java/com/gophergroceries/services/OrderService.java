package com.gophergroceries.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gophergroceries.model.AddToCartForm;
import com.gophergroceries.model.Order;
import com.gophergroceries.model.dao.OrderSummary;
import com.gophergroceries.model.entities.OrdersEntity;
import com.gophergroceries.model.repository.OrdersRepository;
import com.gophergroceries.results.AddToOrderResult;

@Service
public class OrderService {

	private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

	@Autowired
	private Order order;

	private final String SESSION_ID = "TestSessionID";
	// private final String EMAIL = "camroe@gmail.com";
	// private final String PAYPAL_CONFIRMATION = "Test PayPal Confirmation";
	private final String CONFIRMATION_ID = "TestConfirmation";

	@Autowired
	private OrdersRepository ordersRepository;

	public OrdersEntity getOrderWithSessionID(String sessionid) {
		return ordersRepository.findOneBySessionID(SESSION_ID);
	}

	public OrdersEntity getOrderWithConfirmationID(String confirmationID) {
		return ordersRepository.findOneByConfirmationID(CONFIRMATION_ID);
	}

	public AddToOrderResult addItemToOrder(AddToCartForm atcf) {
		OrdersEntity orderEntity = ordersRepository.findOneBySessionID(atcf.getSessionID());
		if (null == orderEntity) {
			return createNewOrder(atcf);
		}
		return addToExisting(orderEntity, atcf);
	}

	private AddToOrderResult addToExisting(OrdersEntity orderEntity, AddToCartForm atcf) {
		AddToOrderResult ator = new AddToOrderResult();
		order.setOrderEntity(orderEntity);
		if (!order.add(atcf)) {
			logger.warn("Product Not Found: actf = " + atcf.toString());
			ator.setError(true);
			ator.setErrorMsg("Attempted to add unknown product to cart");
		}
		else {
			OrderSummary os = new OrderSummary(order);
			ator.setOrderSummary(os);
		}
		return ator;
	}

	private AddToOrderResult createNewOrder(AddToCartForm atcf) {
		AddToOrderResult ator = new AddToOrderResult();
		ator.setError(true);
		ator.setErrorMsg("Create New Order Has not been implemented yet");
		return ator;
	}

}
