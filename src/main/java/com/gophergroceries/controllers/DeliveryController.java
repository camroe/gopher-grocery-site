package com.gophergroceries.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.gophergroceries.cookies.CookieMgr;
import com.gophergroceries.cookies.GopherCookie;
import com.gophergroceries.cookies.GopherCookieFactory;
import com.gophergroceries.model.dao.JsonUtils;
import com.gophergroceries.results.ConfirmedOrderSummaryResult;
import com.gophergroceries.results.OrderSummaryResult;
import com.gophergroceries.services.ConfirmedOrderService;
import com.gophergroceries.services.DeliveryService;
import com.gophergroceries.services.OrderService;

@Controller
public class DeliveryController {
	private static final Logger logger = LoggerFactory.getLogger(DeliveryController.class);

	private static final String PAYMENT_TYPE_PAYPAL = "paypal";
	private static final String PAYMENT_TYPE_CONTACT = "contactforpayment";

	@Autowired
	DeliveryService deliveryService;

	@Autowired
	OrderService orderService;

	@Autowired
	ConfirmedOrderService confirmedOrderService;

	@RequestMapping(value = "/v1/delivery", method = RequestMethod.GET)
	public String getDelivery(Model model,
			HttpServletResponse httpServletResponse,
			HttpServletRequest httpServletRequest) {
		Cookie cookie = CookieMgr.getCookie(GopherCookie.GOPHER_COOKIE_NAME, httpServletRequest);
		GopherCookie gopherCookie = new GopherCookie(cookie);
		OrderSummaryResult osr = orderService.getOrderSummary(gopherCookie);
		model.addAttribute("orderSummaryResult", osr);
		// model.addAttribute("osJson", getJSon(osr));
		model.addAttribute("osJson", JsonUtils.javascriptEscapedJsonStringFromObject(osr));

		return "delivery";
	}

	@RequestMapping(value = "/v1/delivery", method = RequestMethod.POST)
	public String postDelivery(Model model,
			@RequestParam("first_name") String firstname,
			@RequestParam("last_name") String lastname,
			@RequestParam("location") String location,
			@RequestParam("unit") String unit,
			@RequestParam("phone") String phone,
			@RequestParam("email") String email,
			@RequestParam("checkindate") String checkindate,
			@RequestParam("comment") String comment,
			HttpServletResponse httpServletResponse,
			HttpServletRequest httpServletRequest) {

		Cookie cookie = CookieMgr.getCookie(GopherCookie.GOPHER_COOKIE_NAME, httpServletRequest);
		GopherCookie gopherCookie = new GopherCookie(cookie);
		logger.trace(firstname);
		logger.trace(lastname);
		logger.trace(location);
		logger.trace(unit);
		logger.trace(phone);
		logger.trace(email);
		logger.trace(checkindate);
		logger.trace(comment);

		OrderSummaryResult osr = deliveryService.setDeliveryInformation(firstname, lastname, location, unit, phone, email,
				checkindate, comment, gopherCookie);
		model.addAttribute("orderSummaryResult", osr);
		// model.addAttribute("osJson", getJSon(osr));
		model.addAttribute("osJson", JsonUtils.javascriptEscapedJsonStringFromObject(osr));

		// TODO: We could return to delivery here if we find there is an error in
		// the delivery form
		// else move to next page.
		return "orderreview";
	}

	@RequestMapping(value = "/v1/delivery/paypal", method = RequestMethod.GET)
	public String payWithPaypal(Model model,
			HttpServletResponse httpServletResponse,
			HttpServletRequest httpServletRequest) {
		return handlePayementMethodRequest(httpServletRequest, model, httpServletResponse,
				DeliveryController.PAYMENT_TYPE_PAYPAL);
	}

	@RequestMapping(value = "/v1/delivery/contactforpayment", method = RequestMethod.GET)
	public String payWithContactLater(Model model,
			HttpServletResponse httpServletResponse,
			HttpServletRequest httpServletRequest) {
		return handlePayementMethodRequest(httpServletRequest, model, httpServletResponse,
				DeliveryController.PAYMENT_TYPE_CONTACT);
	}

	private String handlePayementMethodRequest(HttpServletRequest httpServletRequest,
			Model model,
			HttpServletResponse httpServletResponse,
			String paymentType) {
		Cookie cookie = CookieMgr.getCookie(GopherCookie.GOPHER_COOKIE_NAME, httpServletRequest);
		GopherCookie gopherCookie = new GopherCookie(cookie);
		OrderSummaryResult osr = orderService.getOrderSummary(gopherCookie);
		model.addAttribute("orderSummaryResult", osr);
		// model.addAttribute("osJson", getJSon(osr));
		model.addAttribute("osJson", JsonUtils.javascriptEscapedJsonStringFromObject(osr));

		String confirmedOrderId = deliveryService.transferOrderToSubmitted(paymentType, gopherCookie,
				httpServletRequest.getServerName());
		if (confirmedOrderId.equals(DeliveryService.FAILED_CONFIRMATION)) {
			return "orderreview";
		}
		else {
			ConfirmedOrderSummaryResult cosr = confirmedOrderService.getConfirmedOrderWithConfirmationID(confirmedOrderId);
			model.addAttribute("confirmedOrderSummaryResult", cosr);
			// model.addAttribute("cosJson",
			// JsonUtils.jsonStringFromObjectPretty(cosr));
			model.addAttribute("cosJson", JsonUtils.javascriptEscapedJsonStringFromObject(cosr));
			model.addAttribute("confirmationid", confirmedOrderId);
			httpServletResponse.addCookie(GopherCookieFactory.clearCookie(gopherCookie.getCookie()));
			if (cosr.isError()) {
				return "couldNotFindOrder";
			}
			return paymentType;
		}
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
