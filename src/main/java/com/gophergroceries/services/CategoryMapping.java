package com.gophergroceries.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

@Service("beanName")
public class CategoryMapping {

	HashMap<String, List<String>> categoryMap = new HashMap<String, List<String>>();

	public CategoryMapping() {
		init();
	}

	public HashMap<String, List<String>> getCategoryMap() {
		return categoryMap;
	}

	private void init() {
		ArrayList<String> asList = new ArrayList<>();
		// Create Subcategory list first then add to category map under key
		// name.
		asList.add("Bread");
		asList.add("Buns");
		// Category Baking
		categoryMap.put("Baking", asList);
	}
}
