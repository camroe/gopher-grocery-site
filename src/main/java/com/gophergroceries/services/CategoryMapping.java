package com.gophergroceries.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gophergroceries.model.Category;
import com.gophergroceries.model.SubCategory;

@Service("beanName")
public class CategoryMapping {

	List<Category> categoryList = new ArrayList<Category>();

	public CategoryMapping() {
		init();
	}

	public List<Category> getCategoryList() {
		return categoryList;
	}

	private void init() {
		//TODO: Consider doing this with Beans.
		ArrayList<SubCategory> subCategoryList = new ArrayList<>();
		
		subCategoryList.add(new SubCategory("Bread"));
		subCategoryList.add(new SubCategory("Buns"));
		categoryList.add(buildCategory("Bakery", subCategoryList));
		
		subCategoryList.clear();
		subCategoryList.add(new SubCategory("Baking Mixes","bakingmixes"));
		subCategoryList.add(new SubCategory("Baking Morsels, Bars & Cocoa","bakingmorsels"));
		subCategoryList.add(new SubCategory("Dough","dough"));		
		categoryList.add(buildCategory("Baking & Cooking Needs", subCategoryList));
	}

	private Category buildCategory(String displayName, String urlAddress, ArrayList<SubCategory> subCategories) {
		Category cat = new Category();
		cat.setDisplayName(displayName);
		cat.setUrlAddress(urlAddress);
		cat.setSubCategories(subCategories);

		return cat;
	}

	private Category buildCategory(String displayName, ArrayList<SubCategory> subCats) {
		return buildCategory(displayName, "#", subCats);
	}
}
