package org.tappoz.bean;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class AvatarPlayer {

	private UserName userName;
	private UserAddress userAddress;
	private String description;
	private Date whenCreated;
	
	public AvatarPlayer(UserName userName, UserAddress userAddress, String description) {
		super();
		this.userName = userName;
		this.userAddress = userAddress;
		this.description = description;
		this.whenCreated = new Date();
	}

	public UserName getUserName() {
		return userName;
	}
	public UserAddress getUserAddress() {
		return userAddress;
	}
	public String getDescription() {
		return description;
	}
	public Date getWhenCreated() {
		return whenCreated;
	}

	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
