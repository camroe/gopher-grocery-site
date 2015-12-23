package com.gophergroceries.controllers;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlerController {
	private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerController.class);

	public static final String DEFAULT_ERROR_VIEW = "error";

	@ExceptionHandler(value = { Exception.class, RuntimeException.class })
	public ModelAndView defaultErrorHandler(HttpServletRequest request, HttpServletResponse httpServletResponse, Exception e) {
		ModelAndView mav = new ModelAndView(DEFAULT_ERROR_VIEW);

		mav.addObject("datetime", new Date());
		mav.addObject("exception", e);
		mav.addObject("exceptionName", e.getMessage());
		mav.addObject("url", request.getRequestURL());
		mav.addObject("httpstatus",httpServletResponse.getStatus());
		
		logger.error("Exception Raised by URL: {} \n {}", request.getRequestURL(), e.getStackTrace().toString());
		return mav;
	}
}