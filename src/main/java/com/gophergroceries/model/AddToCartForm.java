package com.gophergroceries.model;

public class AddToCartForm {

	private String cartkey;
	private String id;
	private String sku;
	private String quantity;
	private String setSessionID;

	public String getCartkey() {
		return cartkey;
	}

	public void setCartkey(String cartkey) {
		this.cartkey = cartkey;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getSessionID() {
		return setSessionID;
	}

	public void setSessionID(String setSessionID) {
		this.setSessionID = setSessionID;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\nID:").append(getId()).append("\nSKU: ").append(getSku()).append("\nQuantity: ").append(getQuantity())
				.append("\nCartKey").append(getCartkey()).append("\n");
		return sb.toString();
	}

}
