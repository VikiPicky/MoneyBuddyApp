package com.registration.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.registration.DAO.ConnectionDB;

@WebServlet("/ActivateAccount")
public class ActivateAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ActivateAccount() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String userEmail = request.getParameter("key1");
		
		Connection con = ConnectionDB.getConnection();
		
		System.out.println("From Activate Account - CONNECTED");
		try {
			PreparedStatement pst = con.prepareStatement("SELECT email, active FROM user WHERE email=" + userEmail + " AND active='0'");
			ResultSet rs = pst.executeQuery();
			
			if (rs.next()) {
				PreparedStatement update = con.prepareStatement("UPDATE user SET active='1' WHERE email=" + userEmail);
				int i = update.executeUpdate();
				if (i == 1) {
					response.sendRedirect("SignIn.html");
				} else {
					response.sendRedirect("index.html");
				}
			}
		} catch (Exception ex) {
			
		}	
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
