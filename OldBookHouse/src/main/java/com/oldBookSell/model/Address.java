package com.oldBookSell.model;

/**
 * This is Address Class that is use to map the required
 * column in address table
 * @author  Kundan,Praveen
 * @version 1.0
 * @since 2020-05-18
*/

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "address")
@SequenceGenerator(name="seq", initialValue=1001, allocationSize=1)
public class Address {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
	private int id;
	
	@Column(name="address")	
	private String address;
	
	@Column(name="address2")
	private String address2;
	
	@Column(name="district")
	private String district;
	
	@Column(name="postal_code")
	private String postalCode;
	
	@Column(name="location")
	private String location;
	
	@Column(name="state")
	private String 	state;

	public Address() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int address_id) {
		this.id = address_id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Address(int id, String address, String address2, String district, String postal_code, String location,
			String state) {
		super();
		this.id = id;
		this.address = address;
		this.address2 = address2;
		this.district = district;
		this.postalCode = postal_code;
		this.location = location;
		this.state = state;
	}
}
