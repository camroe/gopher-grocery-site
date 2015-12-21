package com.gophergroceries.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

	/**
	 * Get the Confirmed Order by Confirmation ID
	 * 
	 * @param confirmationid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/v1/customerconfirmedorders/{confirmationid}", method = RequestMethod.GET)
	public String getConfirmedWithSession(@PathVariable("confirmationid") String confirmationid, Model model) {
		/*
		 * At this point, the customer has supposedly clicked on the email link sent
		 * to him by the Confirm order.
		 */

		ConfirmedOrderSummaryResult cosr = confirmedOrderService.getConfirmedOrderWithConfirmationID(confirmationid);
		
			model.addAttribute("confirmedOrderSummaryResult", cosr);
			model.addAttribute("confirmationid", confirmationid);
			String cosJson = JsonUtils.JsonStringFromObject(cosr);
			logger.info("JSON IS: " + cosJson);
			model.addAttribute("cosJson", cosJson);
			if (cosr.isError()) {
			return "couldNotFindOrder";
			}
		return "confirmedorderreview";

	}
}
