package com.gophergroceries.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
@Table(name = "subcategory")
public class SubCategoryEntity {
	@Id
	@GeneratedValue
	private Integer id;

	/*
	 * The @ManyToOne side is always the owner of the relationship. There is no
	 * way to use the mapped by attribute inside the @ManyToOne annotation
	 */
	@ManyToOne
	@JoinColumn(name = "subcategoryentity_id")
	@JsonBackReference
	private CategoryEntity cat;

	private String displayName;

	private String urlAddress;

	public CategoryEntity getCat() {
		return cat;
	}

	public void setCat(CategoryEntity cat) {
		this.cat = cat;
	}

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

	@Override
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "{}"; // empty JSON object.
		}
	}
}
