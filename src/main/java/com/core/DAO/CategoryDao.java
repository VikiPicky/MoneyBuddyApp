package com.core.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.core.model.CategoryBean;
import com.registration.DAO.ConnectionDB;
import com.registration.model.UserBean;

public class CategoryDao {
	public CategoryDao() {
	}

	public boolean AddCategory(CategoryBean category) {

		Connection con = ConnectionDB.getConnection();

		System.out.println("From CatrgoryDao - CONNECTED");

		String categoryName = category.getCategoryName();
		int userId = category.getUserId();

		try {
			String INSERT_CATEGORY_SQL = "INSERT INTO CATEGORY" +
		            "  (firstName, lastName) VALUES " +
		            " (?, ?);";
		 
		 PreparedStatement preparedStatement = con.prepareStatement(INSERT_CATEGORY_SQL);
		 preparedStatement.setString(1, categoryName);
		 preparedStatement.setInt(2, userId);

		} catch (Exception ex) {
			System.out.println("From CatrgoryDao" + ex);
		}
		return true;
	}
}
