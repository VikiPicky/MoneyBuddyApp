package com.registration.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.registration.DAO.UserDao;
import com.registration.model.UserBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SignUpServlet() {
		System.out.println("fffff");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String htmlResponse = "<html><h3>Check and confirm email</h3></html>";
		PrintWriter writer = response.getWriter();
		writer.write(htmlResponse);
	}

	// @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		String admin = request.getParameter("admin");
		
		Integer adminValue = null;
		if("ON".equals(admin)){
			adminValue = 1;
			} else{
				adminValue = 0;
			}
		
		
		System.out.println("admin value " + adminValue);

		String hashedPwd;
		try {
			hashedPwd = createHash(password);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new ServletException("SignUp Servlet - hashing did not work");
		}

		UserBean user = new UserBean();

		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setUserName(userName);
		user.setPassword(hashedPwd);
		user.setEmail(email);
		user.setTelephone(telephone);
		user.setAdmin(adminValue);

		UserDao userDao = new UserDao();
		String registeredUser = userDao.RegisterUser(user);

		System.out.println("From SignUp Servlet - Registered");

		try {
			if (registeredUser.equals("SUCCESS")) {
				String htmlResponse = "<html><h3>" + "Hello " + firstName + " " + lastName
						+ "! Check your Email for validation link and start using MoneyBuddy today." + "</h3></html>";
				PrintWriter writer = response.getWriter();
				writer.write(htmlResponse);

				System.out.println("From SignUp Servlet - Email sent");
			} else {

				response.sendRedirect("index.html");
			}

		} catch (Exception ex) {
			System.out.println("User registered" + ex);
		}
	}

	public static String createHash(String password) throws NoSuchAlgorithmException {

		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] messageDigest = md.digest(password.getBytes());
		BigInteger no = new BigInteger(1, messageDigest);

		String hashtext = no.toString(16);
		while (hashtext.length() < 32) {
			hashtext = "0" + hashtext;
		}
		return hashtext;

	}
}
