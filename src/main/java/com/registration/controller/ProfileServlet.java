package com.registration.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.registration.DAO.ConnectionDB;

@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ProfileServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		HttpSession session = request.getSession(true);
		String email = (String) session.getAttribute("session_user");
		
		Connection con = ConnectionDB.getConnection();
		
		try {
		PreparedStatement pst = con.prepareStatement("SELECT firstname, lastname, username, email, telephone FROM user WHERE email='" + email + "' AND active='1';");
		ResultSet rs = pst.executeQuery();
		
		while (rs.next()) {
			String firstname = rs.getString("firstname");
			String lastname = rs.getString("lastname");
			String username = rs.getString("username");
			String dbemail = rs.getString("email");
			String telephone = rs.getString("telephone");
			
			response.setContentType("text/html");
		    PrintWriter out = response.getWriter();
		    
		    out.print("<html><body><h1>See you Profile below</h1>"
		    		+ "<body>First Name :: " + firstname);
			out.print("<br>");
			out.print("Last Name :: " + lastname);
			out.print("<br>");
			out.print("User Name :: " + username);
			out.print("<br>");
			out.print("Email :: " + dbemail);
			out.print("<br>");
			out.print("Contact No :: " + telephone);
			out.print("<br>");
			out.print("<span class=\"psw\">Change <a href=\"PwdReset_PwdInput.html\">password?</a></span>");
			out.print("<br>");
			out.print("<br>");
			out.print("<br>");
			out.print("<button onclick=\"history.back()\">Go Home</button>");
	        
	        out.println("</center></body></html>");
	        out.close();
			
		}
		
		} catch (Exception ex){
			
		}
		
	

		
		System.out.println("Profile Servlet: Connected");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
