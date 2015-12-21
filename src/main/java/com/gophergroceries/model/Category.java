package com.gophergroceries.model;

import java.util.ArrayList;

public class Category {

	
	private String displayname;
	private String urladdress = "#"; //default to nothing for Category
	private ArrayList<SubCategory> subCategories;
	
	
	
	public String getDisplayname() {
		return displayname;
	}
	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}
	public String getUrladdress() {
		return urladdress;
	}
	public void setUrladdress(String urladdress) {
		this.urladdress = urladdress;
	}
	public ArrayList<SubCategory> getSubCategories() {
		return subCategories;
	}
	public void setSubCategories(ArrayList<SubCategory> subCategories) {
		this.subCategories = subCategories;
	}
	
	
	
}
