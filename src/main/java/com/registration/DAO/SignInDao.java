package com.registration.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.registration.model.SignInBean;

public class SignInDao {

	public SignInDao(){}
	
	public static String SignInCheck(SignInBean signInbean) {
		
		Connection con = ConnectionDB.getConnection();
		
		System.out.println("From SignIN Servlet - CONNECTED");
		
		String email = signInbean.getEmail();
		String password = signInbean.getPassword();
		String hashedLoginPwd = signInbean.getHashedLoginPwd();
		
		
		try {
			String sqlQuery = "SELECT * from  user WHERE email='" + email + "' AND password='"+ hashedLoginPwd + "' AND active=1 ;";
			PreparedStatement pst = con.prepareStatement(sqlQuery);
			pst.setString(1, email);
			pst.setString(2, hashedLoginPwd);
			
			ResultSet rs = pst.executeQuery(sqlQuery);
			String dbemail = rs.getString("email");
			String dbpassword = rs.getString("password");
			
			if (dbemail.equalsIgnoreCase(email) && dbpassword.equalsIgnoreCase(dbpassword)) {
				return "SUCCESS";
			}
			return "ERROR";
			
		} catch(Exception ex) {
			System.out.println("SignInDao " + ex);
		}		
		return "ERROR";		
	}
	
}
