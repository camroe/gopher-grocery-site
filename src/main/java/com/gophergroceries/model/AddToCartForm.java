package com.gophergroceries.model;

public class AddToCartForm {

	private String cartkey;
	private String id;
	private String sku;
	private String quantity;

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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("ID:").append(getId()).append("\nSKU: ").append(getSku()).append("\nQuantity: ").append(getQuantity())
				.append("\nCartKey").append(getCartkey()).append("\n");
		return sb.toString();
	}

}
