package com.gophergroceries.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gophergroceries.results.OrderSummaryResult;
import com.gophergroceries.services.DeliveryService;
import com.gophergroceries.services.OrderService;

@Controller
public class DeliveryController {
	private static final Logger logger = LoggerFactory.getLogger(DeliveryController.class);

	@Autowired
	DeliveryService deliveryService;
	@Autowired
	OrderService orderService;

	@RequestMapping(value = "/v1/delivery", method = RequestMethod.GET)
	public String getDelivery(Model model) {
		OrderSummaryResult osr = orderService.getOrderSummary();
		String osJson = getJSon(osr);
		model.addAttribute("orderSummaryResult", osr);
		model.addAttribute("osJson", osJson);
		return "delivery";
	}

	@RequestMapping(value = "/v1/delivery", method = RequestMethod.POST)
	public String postDelivery(Model model,
			@RequestParam("first_name") String firstName,
			@RequestParam("last_name") String lastName,
			@RequestParam("location") String location,
			@RequestParam("unit") String unit,
			@RequestParam("phone") String phone,
			@RequestParam("email") String email,
			@RequestParam("comment") String comment)

	{
		logger.trace(firstName);
		logger.trace(lastName);
		logger.trace(location);
		logger.trace(unit);
		logger.trace(phone);
		logger.trace(email);
		logger.trace(comment);
		OrderSummaryResult osr = deliveryService.setDeliveryInformation(firstName, lastName, location, unit, phone, email,
				comment);
		model.addAttribute("orderSummaryResult", osr);
		model.addAttribute("osJson", getJSon(osr));
		//TODO: We could return to delivery here if we find there is an error in the delivery form
		//else move to next page.
		return "orderreview";
	}

	@RequestMapping(value = "/v1/delivery/paypal", method = RequestMethod.GET)
	public String getpaypal(Model model) {
		OrderSummaryResult osr = orderService.getOrderSummary();
		String osJson = getJSon(osr);
		//TODO: Move order to Ordered Table.
		model.addAttribute("orderSummaryResult", osr);
		model.addAttribute("osJson", osJson);
		return "paypal";
	}

	private String getJSon(OrderSummaryResult osr) {
		String returnJson = "";
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			returnJson = objectMapper.writeValueAsString(osr);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		logger.info("Delivery -> JSON IS: \n" + returnJson);
		return returnJson;
	}
}
