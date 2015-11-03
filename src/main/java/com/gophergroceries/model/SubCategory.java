package com.gophergroceries.model;

public class SubCategory {

	private String displayName;
	private String idName;		 // This should represent a name for a list of
														 // products.

	
	public SubCategory(String name) {
		this.displayName = name;
		this.idName = name;
	}

	public SubCategory(String displayName, String idName) {
		this.displayName = displayName;
		this.idName = idName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getIdName() {
		return idName;
	}

	public void setIdName(String idName) {
		this.idName = idName;
	}

}
