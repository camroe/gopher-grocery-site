package com.gophergroceries.services;

import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

import com.gophergroceries.model.AddToCartForm;
import com.gophergroceries.model.Order;
import com.gophergroceries.model.dao.OrderSummary;
import com.gophergroceries.model.entities.OrderLinesEntity;
import com.gophergroceries.model.entities.OrdersEntity;
import com.gophergroceries.model.entities.OrdersEntityFactory;
import com.gophergroceries.model.repository.OrdersRepository;
import com.gophergroceries.results.AddToOrderResult;
import com.gophergroceries.results.OrderSummaryResult;

@Service
public class OrderService {

	private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

	@Autowired
	private Order order;

	@Autowired
	private OrdersRepository ordersRepository;

	// private final String SESSION_ID = "TestSessionID";
	// private final String EMAIL = "camroe@gmail.com";

	public OrdersEntity getOrderWithSessionID(String sessionid) {
		return ordersRepository.findOneBySessionID(sessionid);
	}

		public OrdersEntity getOrderWithUserName(String username) {
		return ordersRepository.findOneByUsername(username);
	}

	public AddToOrderResult addItemToOrder(AddToCartForm atcf) {
		OrdersEntity orderEntity = null;
		if ("anonymousUser".equals(atcf.getUsername())) {
			orderEntity = getOrderWithSessionID(atcf.getSessionID());
		}
		else {
			orderEntity = getOrderWithUserName(atcf.getUsername());
		}
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
			ator.setError(false);
			ator.setErrorMsg("Success: Add to existing order");
			OrderSummary os = new OrderSummary(order);
			ator.setOrderSummary(os);
		}
		logger.debug("ATOR: " + ator);
		return ator;
	}

	private AddToOrderResult createNewOrder(AddToCartForm atcf) {
		AddToOrderResult ator = new AddToOrderResult();
		// Set up new OrdersEntity
		OrdersEntity oe = OrdersEntityFactory.empty();
		// Set up new OrderLinesEntity
		order.setOrderEntity(oe);

		// Try to add atcf to order.
		if (order.add(atcf)) {
			ator.setError(false);
			ator.setErrorMsg("Success: Create new Order");
		}
		else {
			ator.setError(true);
			ator.setErrorMsg("Error Adding to Cart. We are looking into it.");
			logger.error("Failed to add to cart in OrderService : atcf =>" + atcf);
		}
		OrderSummary os = new OrderSummary(order);
		ator.setOrderSummary(os);
		logger.debug("ATOR: " + ator);
		return ator;
	}

	public OrderSummaryResult getOrderSummary() {
		OrderSummaryResult osr = new OrderSummaryResult();
		String session = RequestContextHolder.currentRequestAttributes().getSessionId();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		OrdersEntity oe = getOEBasedOn(name, session);
		if (null == oe) {
			oe = OrdersEntityFactory.empty();
		}
		order.setOrderEntity(oe);
		OrderSummary os = new OrderSummary(order);
		osr.setError(false);
		osr.setErrorMsg("Success");
		osr.setOrderSummary(os);
		return osr;
	}

	private OrdersEntity getOEBasedOn(String name, String session) {
		if (name.equals("anonymousUser"))
			return getOrderWithSessionID(session);
		else
			return getOrderWithUserName(name);
	}

	public OrderSummaryResult updateOrder(OrdersEntity modifiedOrdersEntity) {
		OrderSummaryResult returnResult = new OrderSummaryResult();

		OrdersEntity oe = ordersRepository.getOne(modifiedOrdersEntity.getId());
		if (null == oe) {
			// order not found by id
			returnResult.setErrorMsg("Could not find Order: " + modifiedOrdersEntity.getId());
			return returnResult; // break out of method.
		}
		for (OrderLinesEntity ol : oe.getOrderLines()) {
			Integer newQuantity = findMatchingOrderLineQuantity(ol.getId(), modifiedOrdersEntity);
			logger.trace("OldQuantity: " + ol.getQuantity());
			logger.trace("NewQuantity: " + newQuantity);
			ol.setQuantity(newQuantity);
		}
		removeZeroQuantityOrderLines(oe);
		ordersRepository.saveAndFlush(oe);

		returnResult.setOrderSummary(new OrderSummary(new Order(oe)));
		returnResult.setError(false);
		returnResult.setErrorMsg("Success");
		return returnResult;

	}

	private void removeZeroQuantityOrderLines(OrdersEntity oe) {
		SortedSet<OrderLinesEntity> setOfOLEs = oe.getOrderLines();

		Iterator<OrderLinesEntity> iterator = setOfOLEs.iterator();
		while (iterator.hasNext()) {
			OrderLinesEntity ole = iterator.next();
			if (ole.getQuantity() == 0) {
				iterator.remove();
			}
		}
		oe.setOrderLines(setOfOLEs);
	}

	private Integer findMatchingOrderLineQuantity(Integer id, OrdersEntity modifiedOrdersEntity) {
		Integer returnValue = new Integer(0);
		Set<OrderLinesEntity> setOfOLEs = modifiedOrdersEntity.getOrderLines();

		for (OrderLinesEntity ole : setOfOLEs) {
			if (ole.getId().equals(id)) {
				returnValue = new Integer(ole.getQuantity());

			}
		}
		return returnValue;
	}

}
