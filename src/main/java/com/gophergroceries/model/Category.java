package com.gophergroceries.model;

import java.util.ArrayList;

public class Category {

	
	private String displayName;
	private String urlAddress = "#"; //default to nothing for Category
	private ArrayList<SubCategory> subCategories;
	
	
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getUrlAddress() {
		return urlAddress;
	}
	public void setUrlAddress(String urlAddress) {
		this.urlAddress = urlAddress;
	}
	public ArrayList<SubCategory> getSubCategories() {
		return subCategories;
	}
	public void setSubCategories(ArrayList<SubCategory> subCategories) {
		this.subCategories = subCategories;
	}
	
	
	
}
