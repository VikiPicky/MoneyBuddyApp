package com.core.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpSession;

import com.core.model.CategoryBean;
import com.registration.DAO.ConnectionDB;
import com.registration.DAO.ProfileDao;
import com.registration.model.UserBean;

public class CategoryDao {
	public CategoryDao() {
	}

	public boolean AddCategory(CategoryBean category, String email) {

		Connection con = ConnectionDB.getConnection();

		String categoryName = category.getCategoryName();
		
		System.out.println("From CatagoryDao - CONNECTED " + email  + " "+ categoryName);
		
		try {

			String SELECT_USER_SQL = "SELECT userid from user  WHERE email='" + email + "' AND active=1 ;";
			PreparedStatement pst = con.prepareStatement(SELECT_USER_SQL);
			ResultSet rs = pst.executeQuery(SELECT_USER_SQL);
			
			System.out.println("From CatagoryDao - selected");
			
			while (rs.next()) {
				int userId = rs.getInt("userid");
				
				System.out.println("From CatagoryDao - CONNECTED " + userId);
				
				String INSERT_CATEGORY_SQL = "INSERT INTO CATEGORY (categoryName, userId) VALUES (?, ?);";

				PreparedStatement preparedStatement = con.prepareStatement(INSERT_CATEGORY_SQL);
				preparedStatement.setString(1, categoryName);
				preparedStatement.setInt(2, userId);
				int recordAdded = preparedStatement.executeUpdate();
				
				System.out.println("From CatagoryDao - record added" + recordAdded);			
			}

		} catch (Exception ex) {
			System.out.println("From CategoryDao" + ex);
		}
		return true;
	}
}
