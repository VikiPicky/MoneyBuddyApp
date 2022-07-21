package com.registration.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.registration.model.UserBean;

public class ProfileDao {

	public UserBean getProfileDetails(String email) {
		UserBean user = null;
		
		System.out.println("Profile Dao : IN"  );
		try {
			Connection con = ConnectionDB.getInstance().getConnection();

			String sql = "select * from user WHERE email=?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, email);
			
			System.out.println("Profile Dao " + email );

			ResultSet set = statement.executeQuery();
			while (set.next()) {
				user = new UserBean();
				user.setUserID(set.getInt("userid"));
				user.setFirstName(set.getString("firstname"));
				user.setLastName(set.getString("lastname"));
				user.setUserName(set.getString("username"));
				user.setTelephone(set.getString("telephone"));
				user.setEmail(set.getString("email"));
			}

		} catch (Exception ex) {
			System.out.println("Profile Servlet: " + ex);

		}
		return user;
	}
}
