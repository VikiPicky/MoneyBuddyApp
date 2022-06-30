package com.core.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale.Category;

import javax.servlet.http.HttpSession;

import com.core.model.CategoryBean;
import com.registration.DAO.ConnectionDB;
import com.registration.DAO.ProfileDao;
import com.registration.model.UserBean;

public class CategoryDao {
	public CategoryDao() {
	}

	public boolean addCategory(CategoryBean category, UserBean userBean) {

		Connection con = ConnectionDB.getConnection();

		String categoryName = category.getCategoryName();

		System.out.println("From CatagoryDao - CONNECTED " + userBean.getEmail() + " " + categoryName);

		try {

			String INSERT_CATEGORY_SQL = "INSERT INTO CATEGORY (categoryName, userId) VALUES (?, ?);";

			PreparedStatement preparedStatement = con.prepareStatement(INSERT_CATEGORY_SQL);
			preparedStatement.setString(1, categoryName);
			preparedStatement.setInt(2, userBean.getUserID());
			int recordAdded = preparedStatement.executeUpdate();

			System.out.println("From CatagoryDao - record added" + recordAdded);

		} catch (Exception ex) {
			System.out.println("From CategoryDao" + ex);
		}
		return true;
	}

	public List<CategoryBean> list(UserBean userBean) throws SQLException {
		List<CategoryBean> listCategory = new ArrayList<>();
		try {
			Connection con = ConnectionDB.getConnection();
			String sql = "SELECT * FROM category WHERE userid=? ORDER BY categoryName;";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1, userBean.getUserID());
			ResultSet result = preparedStatement.executeQuery();

			while (result.next()) {
				int categoryId = result.getInt("categoryid");
				String categoryName = result.getString("categoryName");
				int userId = result.getInt("userId");
				CategoryBean categoryBean = new CategoryBean(categoryId, categoryName, userId);

				listCategory.add(categoryBean);

			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return listCategory;
	}
}
