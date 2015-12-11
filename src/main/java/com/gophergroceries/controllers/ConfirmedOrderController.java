package com.gophergroceries.controllers;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gophergroceries.model.dao.JsonUtils;
import com.gophergroceries.results.ConfirmedOrderSummaryResult;
import com.gophergroceries.services.ConfirmedOrderService;
import com.gophergroceries.services.EncryptionDecryptionService;

@Controller
public class ConfirmedOrderController {
	private static final Logger logger = LoggerFactory.getLogger(ConfirmedOrderController.class);

	@Autowired
	ConfirmedOrderService confirmedOrderService;

	@Autowired
	EncryptionDecryptionService edService;

	@RequestMapping(value = "/v1/confirmedorders", method = RequestMethod.GET)
	public String getConfirmedOrder(Locale locale, Model model) {
		ConfirmedOrderSummaryResult cosr = confirmedOrderService.getConfirmedOrder();
		model.addAttribute("confirmedOrderSummaryResult", cosr);
		String cosJson = JsonUtils.JsonStringFromObject(cosr);
		logger.info("JSON IS: " + cosJson);
		model.addAttribute("cosJson", cosJson);
		if (cosr.isError()) {
			// TODO: Return Error Page
		}
		return "confirmedorderreview";
	}

	@RequestMapping(value = "/v1/customerconfirmedorders", method = RequestMethod.GET)
	public String getConfirmedWithSession(@RequestParam String sessionID, Model model) {
		/*
		 * At this point, the customer has supposedly clicked on the email link sent
		 * to him by the Confirm order.
		 */
		String lookupSessionID = "";
		try {
			lookupSessionID = edService.decrypt(sessionID);
		} catch (Exception e) {
			logger.error("Trying to Lookup order based on Base64/encrypted sessionID: " + sessionID);
			e.printStackTrace();
		}
		ConfirmedOrderSummaryResult cosr = confirmedOrderService.getConfirmedOrder(lookupSessionID);
		model.addAttribute("confirmedOrderSummaryResult", cosr);
		String cosJson = JsonUtils.JsonStringFromObject(cosr);
		logger.info("JSON IS: " + cosJson);
		model.addAttribute("cosJson", cosJson);
		if (cosr.isError()) {
			// TODO: Return Error Page
		}
		return "confirmedorderreview";

	}
}
