package org.tappoz.bean;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class UserAddress {
	private String city;
	private String postCode;
	private String country;
	private String address;
	
	public UserAddress(String city, String postCode, String country,
			String address) {
		this.city = city;
		this.postCode = postCode;
		this.country = country;
		this.address = address;
	}
	
	public String getCity() {
		return city;
	}
	public String getPostCode() {
		return postCode;
	}
	public String getCountry() {
		return country;
	}
	public String getAddress() {
		return address;
	}
	
	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
