package com.registration.DAO;

import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class SendingEmail {
	private String userEmail;

	public SendingEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public void sendEmail() {
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
			message.setText("Registered successfully.Please verify your account using by following the link "
					+ " http://localhost:8080/Test/ActivateAccount?key1=" + userEmail);
			Transport.send(message);
		} catch (Exception ex) {
			System.out.println("Sending Email to User" + ex);
		}
	}
}
