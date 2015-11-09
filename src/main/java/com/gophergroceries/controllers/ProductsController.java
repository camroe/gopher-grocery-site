package com.gophergroceries.controllers;

import java.util.List;

import org.apache.commons.lang3.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gophergroceries.model.entities.ProductEntity;
import com.gophergroceries.services.ProductsService;

@Controller
@RequestMapping("/v1/products")
public class ProductsController {

	@Autowired
	private ProductsService productService;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<ProductEntity> getProducts() {
		return productService.getProducts();
	}

	@RequestMapping(value = "{id}")
	public @ResponseBody ProductEntity getProduct(@PathVariable("id") Integer id) {
		return productService.getProduct(id);
	}

	@RequestMapping(value = "/category/{subCategory}")
	public String getSubCategory(@PathVariable("subCategory") String subCategory, Model model) {
		model.addAttribute("subCategoryName", WordUtils.capitalize(subCategory));
		model.addAttribute("selectedProducts", productService.getProductsInCategory(subCategory));
		return "results";
	}
}
