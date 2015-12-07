package com.gophergroceries.controllers;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gophergroceries.model.dao.JsonUtils;
import com.gophergroceries.results.ConfirmedOrderSummaryResult;
import com.gophergroceries.services.ConfirmedOrderService;

@Controller
public class ConfirmedOrderController {
	private static final Logger logger = LoggerFactory.getLogger(ConfirmedOrderController.class);

	@Autowired
	ConfirmedOrderService confirmedOrderService;
	
	@RequestMapping(value = "/v1/confirmedorders", method = RequestMethod.GET)
	public String getConfirmedOrder(Locale locale, Model model) {
		ConfirmedOrderSummaryResult cosr = confirmedOrderService.getConfirmedOrder();
		model.addAttribute("confirmedOrderSummaryResult",cosr);
		String cosJson = JsonUtils.JsonStringFromObject(cosr);
		logger.info("JSON IS: " + cosJson);
		model.addAttribute("cosJson",cosJson);
		if (cosr.isError()){
			//TODO: Return Error Page
		}
		return "confirmedorderreview";
	}
	
	
	
	
}
