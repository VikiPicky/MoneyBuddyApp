package com.registration.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SignInServlet")
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SignInServlet() {
        super();
    
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userNameProvided = request.getParameter("userName");
		String passwordProvided = request.getParameter("password");
		
		String hashedPwd;
		try {
			hashedPwd = createHash(passwordProvided);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new ServletException("Извините, всё сломалось.!");
		}

	}
	private static String createHash(String passwordProvided) throws NoSuchAlgorithmException {

		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] messageDigest = md.digest(passwordProvided.getBytes());
		BigInteger no = new BigInteger(1, messageDigest);

		String hashedPWDCompare = no.toString(16);
		while (hashedPWDCompare.length() < 32) {
			hashedPWDCompare = "0" + hashedPWDCompare;
		}
		return hashedPWDCompare; //// compare to DB insance in the DB

	}
}
