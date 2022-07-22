package com.registration.model;

public class UserBuilder {
	private int userID;
	private String firstName;
	private String lastName;
	private String userName;
	private String email;
	private String telephone;

	public UserBuilder setUserID(int userID) {
		this.userID = userID;
		return this;
	}

	public UserBuilder setName(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		return this;
	}

	public UserBuilder setPersonalDetails(String userName, String email, String telephone) {
		this.userName = userName;
		this.email = email;
		this.telephone = telephone;
		return this;
	}

	public UserBean createUserBean() {
		return new UserBean(userID, firstName, lastName, userName, null, email, telephone, 0, 0);
	}
}
