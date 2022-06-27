package com.registration.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.registration.DAO.ConnectionDB;

@WebServlet("/PwdResetInput_type")
public class PwdResetInput_type extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PwdResetInput_type() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
		
		String email = request.getParameter("email");
		String newPassword = request.getParameter("password");
		
		String hashedNewPwd;

		try {
			hashedNewPwd = createHash(newPassword);

			System.out.println("Pwd Input: Hashed New Pwd created");

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new ServletException("Swd Input Servlet- hashing did not work");
		}

		pwdUpdate(email, hashedNewPwd, response);
				
	}
	
	private void pwdUpdate(String email, String hashedNewPwd, HttpServletResponse response) {
		Connection con = ConnectionDB.getConnection();
		System.out.println("Pwd Input: Connected");
		
		try {
			PreparedStatement pst = con.prepareStatement("SELECT email, active, password FROM user WHERE email='" + email + "' AND active='1';");
			ResultSet rs = pst.executeQuery();


			if (rs.next()) {
				PreparedStatement update = con
						.prepareStatement("UPDATE user SET password='" + hashedNewPwd +"' WHERE active='1' AND email='" + email + "';");
				
				int i = update.executeUpdate();
				System.out.println("Password updated " + i);
				if (i > 0) {
					
					response.sendRedirect("Home.jsp");
				} else {
					response.sendRedirect("index.html");
				}
			} else {
				System.out.println("Password Update : No suitable records");
			}
		} catch (Exception ex) {
			System.out.println("Password Update " + ex.getMessage());
		}
		
	}

	private static String createHash(String newPassword) throws NoSuchAlgorithmException {

		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] messageDigest = md.digest(newPassword.getBytes());
		BigInteger no = new BigInteger(1, messageDigest);

		String hashtext = no.toString(16);
		while (hashtext.length() < 32) {
			hashtext = "0" + hashtext;
		}
		return hashtext;
	}
}
