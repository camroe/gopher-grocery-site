package com.gophergroceries.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	// @RequestMapping(value = "/v1/confirmedorders", method = RequestMethod.GET)
	// public String getConfirmedOrder(Locale locale, Model model,
	// HttpServletResponse httpServletResponse,
	// HttpServletRequest httpServletRequest) {
	//
	// Cookie cookie = CookieMgr.getCookie(GopherCookie.GOPHER_COOKIE_NAME,
	// httpServletRequest);
	// GopherCookie gopherCookie = new GopherCookie(cookie);
	// ConfirmedOrderSummaryResult cosr =
	// confirmedOrderService.getConfirmedOrder(gopherCookie);
	// model.addAttribute("confirmedOrderSummaryResult", cosr);
	// String cosJson = JsonUtils.JsonStringFromObject(cosr);
	// logger.info("JSON IS: " + cosJson);
	// model.addAttribute("cosJson", cosJson);
	// if (cosr.isError()) {
	// // TODO: Return Error Page
	// }
	// return "confirmedorderreview";
	// }

	@RequestMapping(value = "/v1/customerconfirmedorders/{confirmationid}", method = RequestMethod.GET)
	public String getConfirmedWithSession(@PathVariable("confirmationid") String confirmationid,
			Model model,
			HttpServletResponse httpServletResponse,
			HttpServletRequest httpServletRequest) {

		ConfirmedOrderSummaryResult cosr = confirmedOrderService.getConfirmedOrder(confirmationid);
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
