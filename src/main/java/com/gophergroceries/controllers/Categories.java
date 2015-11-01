package com.gophergroceries.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gophergroceries.services.CategoryMapping;

@Controller
@RequestMapping("/v1/catalog")
public class Categories {

	@Autowired
	CategoryMapping catMap;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody CategoryMapping getCategoryMapping() {
		return catMap;
	}

}
