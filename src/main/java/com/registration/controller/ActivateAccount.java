package com.registration.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.registration.DAO.ConnectionDB;

@WebServlet("/ActivateAccount")
public class ActivateAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ActivateAccount() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("ActivateAccount: " + e);
		}
		String userEmail = request.getParameter("key1");
		int admin = Integer. parseInt(request. getParameter("key2"));
		
		Connection con = ConnectionDB.getConnection();

		System.out.println("ActivateAccount: Connected");

		try {
			PreparedStatement pst = con.prepareStatement("SELECT email, active FROM user WHERE email='" + userEmail + "' AND active='0';");
			ResultSet rs = pst.executeQuery();


			if (rs.next()) {
				PreparedStatement update = con
						.prepareStatement("UPDATE user SET active='1' WHERE email='" + userEmail + "';");
				
				int i = update.executeUpdate();
				System.out.println("Activate account " + i);
				if (i > 0) {
					
					response.sendRedirect("SignIn.html");
				} else {
					response.sendRedirect("index.html");
				}
			} else {
				System.out.println("Activate account : No suitable records");
			}
		} catch (Exception ex) {
			System.out.println("ActivateAccount:4" + ex.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
