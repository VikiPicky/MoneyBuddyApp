package com.registration.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@WebServlet("/PwdEmailLink")
public class PwdEmailLink extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PwdEmailLink() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);

		System.out.println("From PwdReset Email Link Servlet - IN");

		String userEmail = request.getParameter("email");

		sendPwdResetEmail(userEmail);

		String htmlResponse = "<html><body><h3>Thank You ! Check your Email for PASSWORD reset link and RETURN TO MoneyBuddy today.</h3></body></html>";
		PrintWriter writer = response.getWriter();
		writer.write(htmlResponse);
	}

	void sendPwdResetEmail(String userEmail) {
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
			message.setSubject("MoneyBuddy Password Reset");
			message.setText("Please follow the link to reset you password "
					+ "http://localhost:8080/TestMaven/PwdResetInput?key=" + userEmail);
			Transport.send(message);

		} catch (Exception ex) {
			System.out.println("Sending Pwd Reset Email to User" + ex);
		}
	}
}
