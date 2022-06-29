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
		System.out.println("From CatagoryDao - CONNECTED");

		String categoryName = category.getCategoryName();

		try {

			String sqlQuery = "SELECT userid from user  WHERE email='" + email + "' AND active=1 ;";
			PreparedStatement pst = con.prepareStatement(sqlQuery);
			ResultSet rs = pst.executeQuery(sqlQuery);
			
			while (rs.next()) {
				int userId = rs.getInt("userid");
				String INSERT_CATEGORY_SQL = "INSERT INTO CATEGORY" + "  (categoryName, userId) VALUES " + " (?, ?);";

				PreparedStatement preparedStatement = con.prepareStatement(INSERT_CATEGORY_SQL);
				preparedStatement.setString(1, categoryName);
				preparedStatement.setInt(1, userId);
			}

		} catch (Exception ex) {
			System.out.println("From CategoryDao" + ex);
		}
		return true;
	}
}
