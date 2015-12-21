package com.gophergroceries.model;

public class SubCategory {

	private String displayname;
	private String idName; // This should represent a name for a list of
												 // products.

	public SubCategory(String name) {
		this.displayname = name;
		this.idName = name;
	}

	public SubCategory(String displayname, String idName) {
		this.displayname = displayname;
		this.idName = idName;
	}

	public String getDisplayname() {
		return displayname;
	}

	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}

	public String getIdName() {
		return idName;
	}

	public void setIdName(String idName) {
		this.idName = idName;
	}

}
