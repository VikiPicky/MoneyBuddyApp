package com.registration.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.registration.DAO.ConnectionDB;

@WebServlet("/PwdResetInput")
public class PwdResetInput extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public PwdResetInput() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String userEmail = request.getParameter("key");
		Connection con = ConnectionDB.getConnection();		
		
		System.out.println("Pwd Input: Connected");
		response.sendRedirect("PwdReset_PwdInput.html");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		String newPassword = request.getParameter("password");
		
		String hashedNewPwd;
		
		try {
			hashedNewPwd = createHash(newPassword);
			
			System.out.println("Pwd Input: Hashed New Pwd created");
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new ServletException("Swd Input Servlet- hashing did not work");
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
