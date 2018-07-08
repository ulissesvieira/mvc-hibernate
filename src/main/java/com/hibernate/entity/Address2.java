package com.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "ADDRESS2")
public class Address2 {
	@Id	
	@Column(name = "EMP_ID")
	@GeneratedValue(generator = "address2_generator")
	@GenericGenerator(name = "address2_generator", strategy = "foreign", 
					  parameters = {@Parameter(name = "property", value = "employee")})
	private Integer id;
	
	@Column(name = "ADDRESS_LINE1")
	private String addressLine1;
	
	@Column(name = "ZIPCODE")
	private String zipcode;
	
	@Column(name = "CITY")
	private String city;
	
	@OneToOne
	@PrimaryKeyJoinColumn
	private Employee2 employee;
	
	public Address2() {}

	public Address2(String addressLine1, String zipcode, String city) {
		super();
		this.addressLine1 = addressLine1;
		this.zipcode = zipcode;
		this.city = city;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Employee2 getEmployee() {
		return employee;
	}

	public void setEmployee(Employee2 employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "Address2 [id=" + id + ", addressLine1=" + addressLine1 + ", zipcode=" + zipcode + ", city=" + city + "]";
	}	
}
