package com.registration.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.registration.model.SignInBean;

public class SignInDao {

	public SignInDao() {
	}

	public boolean ActiveMatchExists(SignInBean signInbean) {

		Connection con = ConnectionDB.getConnection();

		String email = signInbean.getEmail();
		String hashedLoginPwd = signInbean.getHashedLoginPwd();

		try {
			String sqlQuery = "SELECT * from  user WHERE email='" + email + "' AND password='" + hashedLoginPwd
					+ "' AND active=1 ;";
			PreparedStatement pst = con.prepareStatement(sqlQuery);

			ResultSet rs = pst.executeQuery(sqlQuery);
			if (rs.last() && rs.getRow() > 0) {
				return true;
			}

		} catch (Exception ex) {
			System.out.println("SignInDao " + ex);
		}
		return false;
	}
}
