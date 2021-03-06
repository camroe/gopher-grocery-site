package com.gophergroceries.controllers;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gophergroceries.cookies.CookieMgr;
import com.gophergroceries.cookies.GopherCookie;
import com.gophergroceries.model.dao.JsonUtils;
import com.gophergroceries.model.entities.OrdersEntity;
import com.gophergroceries.results.OrderSummaryResult;
import com.gophergroceries.services.OrderService;

@Controller
public class OrdersController {
	private static final Logger logger = LoggerFactory.getLogger(OrdersController.class);

	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "/v1/orderAPI/orders", method = RequestMethod.GET)
	public String displayOrderPage(Locale locale, Model model,
			HttpServletResponse httpServletResponse,
			HttpServletRequest httpServletRequest) {
		Cookie cookie = CookieMgr.getCookie(GopherCookie.GOPHER_COOKIE_NAME, httpServletRequest);
		GopherCookie gopherCookie = new GopherCookie(cookie);
		OrderSummaryResult osr = orderService.getOrderSummary(gopherCookie);
		model.addAttribute("orderSummaryResult", osr);
		ObjectMapper objectMapper = new ObjectMapper();
		String osJson = "";
		try {
			osJson = objectMapper.writeValueAsString(osr);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("Outgoing JSON from /v1/orderAPI/orders:GET is : " + JsonUtils.jsonStringFromObjectPretty(osr));
		// model.addAttribute("osJson",
		// JsonUtils.javascriptEscapedJsonString(osJson));
		model.addAttribute("osJson", JsonUtils.javascriptEscapedJsonString(osJson));

		return "order";
	}

	@RequestMapping(value = "/v1/orderAPI/orders", method = RequestMethod.POST)
	public String displayUpdatedOrderPage(HttpServletRequest request,
			Locale locale,
			Model model) {
		String jsonBody = "";
		ObjectMapper objectMapper = new ObjectMapper();
		OrdersEntity oe = null;
		try {
			jsonBody = IOUtils.toString(request.getInputStream());
			Object obj = objectMapper.readValue(jsonBody, Object.class);
			logger.info(
					"Incomming - Modified ObjectEntity:" + objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj));
			oe = objectMapper.readValue(jsonBody, OrdersEntity.class);
			logger.info("Updating:" + oe.getSessionID());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		OrderSummaryResult os = orderService.updateOrder(oe);

		// OrderSummaryResult os = orderService.getOrderSummary();
		model.addAttribute("orderSummaryResult", os);
		String osJson = "";
		try {
			osJson = objectMapper.writeValueAsString(os);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		try {
			logger.info("OutGoing JSON from /v1/orderAPI/orders:POST : " + objectMapper.writerWithDefaultPrettyPrinter()
					.writeValueAsString(objectMapper.readValue(osJson, Object.class)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// model.addAttribute("osJson", osJson);
		model.addAttribute("osJson", JsonUtils.javascriptEscapedJsonString(osJson));
		return "order";
	}

}
