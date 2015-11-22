package com.gophergroceries.controllers;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gophergroceries.results.OrderSummaryResult;
import com.gophergroceries.services.OrderService;

@Controller
public class OrdersController {
	private static final Logger logger = LoggerFactory.getLogger(OrdersController.class);

	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "/v1/orderAPI/orders", method = RequestMethod.GET)
	public String displayOrderPage(Locale locale, Model model) {
		logger.info("OrderPage(GET) The client locale is {}.", locale);
		OrderSummaryResult os = orderService.getOrderSummary();
		model.addAttribute("orderSummaryResult", os);
		ObjectMapper objectMapper = new ObjectMapper();
		String osJson ="";
		try {
			osJson = objectMapper.writeValueAsString(os);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("JSON IS: " + osJson);
		model.addAttribute("osJson",osJson);				
		// This maps to webapp/WEB-INF/views/order.jsp based on config in
		// servlet-context.xml
		return "order";
	}

	@RequestMapping(value="/v1/orderAPI/orders", method= RequestMethod.POST)
	public String displayUpdatedOrderPage(Locale locale, Model model) {
		logger.info("OrderPage(GET) The client locale is {}.", locale);
		logger.info("Model as is:" + model.toString());
		//Same as Get
		OrderSummaryResult os = orderService.getOrderSummary();
		model.addAttribute("orderSummaryResult", os);

		return "order";
	}
	
}
