package com.registration.model;

public class UserBean {
	private int userID;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String email;
	private String telephone;
	private int adminValue;
	private int active;

	public UserBean() {
		super();
	}

	public UserBean(int userID, String firstName, String lastName, String userName, String password, String email,
			String telephone, int adminValue, int active) {
		super();
		this.userID = userID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.telephone = telephone;
		this.adminValue = adminValue;
		this.active = active;
	}

	public UserBean(int userID, String firstName, String lastName, String userName, String email, String telephone) {
		this(userID, firstName, lastName, userName, null, email, telephone, 0, 0);
	}

	public UserBean(String firstName, String lastName, String userName, String email, String telephone) {
		this(0, firstName, lastName, userName, email, telephone);
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public int getAdminValue() {
		return adminValue;
	}

	public void setAdminValue(int adminValue) {
		this.adminValue = adminValue;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getAdmin() {
		return adminValue;
	}

	public void setAdmin(int adminValue) {
		this.adminValue = adminValue;
	}
}