package com.userManagement.DAO;

import java.util.Properties;

import com.registration.model.UserBean;

import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class UserManagement_SendValidationEmail {
	
	private UserBean userBean;

	public UserManagement_SendValidationEmail(UserBean userBean) {
		this.userBean = userBean;

	}
	public void sendEmail(String userEmail, String tempPassword) {
		String email = "moneybuddyoop@gmail.com";
		String password = "nkrvmcwimqzbdoww";
		String host = "smtp.gmail.com";

		// Get system properties
		Properties properties = System.getProperties();

		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		Session session = Session.getInstance(properties, new jakarta.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(email, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(email));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
			message.setSubject("MoneyBuddy Email Verification");
			message.setText("Registered successfully.Use temporary password to verify your account " + tempPassword + ". To verify your account follow the link "
					+ "http://localhost:8080/TestMaven/ActivateAccount?key1=" + userEmail + "&key2=" + userBean.getAdmin());
			Transport.send(message);
		} catch (Exception ex) {
			System.out.println("Sending Email to User" + ex);
		}
	}
}
