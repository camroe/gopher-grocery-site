package com.gophergroceries.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gophergroceries.model.entities.CategoryEntity;
import com.gophergroceries.services.CategoryMappingService;

@Controller
@RequestMapping("/v1/categories")
public class CategoriesController {

	@Autowired
	CategoryMappingService catMap;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<CategoryEntity> getCategories() {
		return catMap.getCategoryList();
	}

}
