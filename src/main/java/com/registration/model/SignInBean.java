package com.registration.model;

public class SignInBean {

	private String email;
	private String password;
	private String hashedLoginPwd;

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHashedLoginPwd() {
		return hashedLoginPwd;
	}
	public void setHashedLoginPwd(String hashedLoginPwd) {
		this.hashedLoginPwd = hashedLoginPwd;
	}
	
	
	
}
