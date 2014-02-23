package com.gurps.mongo.repository;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "customer")
public class Customer {

	@Id
	private String id;
	
	@Field("firstName")
	private String firstName;
	
	@Field("lastName")
	private String lastName;
	
	@Field("address")
	private Address address;
	
	@Field("hobbies")
	private List<String> hobbies;
	
	public List<String> getHobbies() {
		return hobbies;
	}

	public void setHobbies(List<String> hobbies) {
		this.hobbies = hobbies;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Customer() {}
	
	public Customer(String firstName, String lastName) {
	    this.firstName = firstName;
	    this.lastName = lastName;
	}	
	
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", address=" + address
				+ ", hobbies=" + hobbies + "]";
	}

	
}
