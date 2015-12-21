package com.gophergroceries.model.entities;

import java.sql.Date;
import java.util.SortedSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.SortNatural;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "confirmedorders")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ConfirmedOrdersEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "email")
	private String email;

	@OneToMany(mappedBy = "confirmedorder", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	@SortNatural
	private SortedSet<ConfirmedOrderLinesEntity> orderlines;

	@Column(name = "creationdate")
	private Date creationdate;

	@Column(name = "sessionid")
	private String sessionID;

	@Column(name = "confirmationid")
	private String confirmationID;

	@Column(name = "paypaynumber")
	private String paypalnumber;

	@Column(name = "username")
	private String username;

	private String firstname;
	private String lastname;
	private String unit;
	private String location;
	private String city = "Salt Lake City";
	private String state = "UT";
	private String zipcode = "84121";
	private String phone;
	private String checkindate;
	private String paymenttype;
	private Integer cartid;

	@Column(name = "comment", length = 500)
	private String comment;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public SortedSet<ConfirmedOrderLinesEntity> getOrderlines() {
		return orderlines;
	}

	public void setOrderlines(SortedSet<ConfirmedOrderLinesEntity> orderlines) {
		this.orderlines = orderlines;
	}

	public Date getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	public String getConfirmationID() {
		return confirmationID;
	}

	public void setConfirmationID(String confirmationID) {
		this.confirmationID = confirmationID;
	}

	public String getPaypalnumber() {
		return paypalnumber;
	}

	public void setPaypalnumber(String paypalnumber) {
		this.paypalnumber = paypalnumber;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCheckindate() {
		return checkindate;
	}

	public void setCheckindate(String checkindate) {
		this.checkindate = checkindate;
	}

	public String getPaymenttype() {
		return paymenttype;
	}

	public void setPaymenttype(String paymenttype) {
		this.paymenttype = paymenttype;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getCartid() {
		return cartid;
	}

	public void setCartid(Integer cartid) {
		this.cartid = cartid;
	}

}
