package com.userManagement.DAO;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import com.registration.DAO.ConnectionDB;
import com.registration.controller.SignUpServlet;
import com.registration.model.UserBean;

public class UserManagementDAO {

	private static final String INSERT_USERS_SQL = "INSERT INTO USER"
			+ "  (firstName, lastName, userName, password, email, telephone, admin, active ) VALUES "
			+ " (?, ?, ?, ?, ?, ?, 0, 0);";

	private static final String SELECT_USER_BY_ID = "SELECT firstName, lastName, userName, password, email, telephone, admin, active from USER where userid=? ;";
	private static final String SELECT_ALL_USERS = "SELECT * from USER;";
	private static final String DELETE_USERS_SQL = "DELETE from USER where userid =? ;";
	private static final String UPDATE_USERS_SQL = "UPDATE USER SET firstName=?, lastName=?, userName=?, email=?, telephone=? WHERE userid=?;";

	public UserManagementDAO() {
	}

	public void insertUser(UserBean userBean) throws SQLException, ServletException {

		System.out.println(INSERT_USERS_SQL);

		try (Connection connection = ConnectionDB.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {		
			
			String tempPassword = "1111";
			String hashedPwd = null;
				try {
				hashedPwd = SignUpServlet.createHash(tempPassword);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
				throw new ServletException("UsermanagementDAO - hashing did not work");
			}
					
			preparedStatement.setString(1, userBean.getFirstName());
			preparedStatement.setString(2, userBean.getLastName());
			preparedStatement.setString(3, userBean.getUserName());
			preparedStatement.setString(4, hashedPwd);
			preparedStatement.setString(5, userBean.getEmail());
			preparedStatement.setString(6, userBean.getTelephone());
						
						
			 int i = preparedStatement.executeUpdate();
			 if (i != 0) {
				 
				 String userEmail = userBean.getEmail();				
				 
				 System.out.println("From UserManagement -" + userEmail);
				 System.out.println("From UserManagement - " + hashedPwd);
				
				 UserManagement_SendValidationEmail sendingEmail = new UserManagement_SendValidationEmail(userBean);
				 sendingEmail.sendEmail(userEmail, tempPassword);
				 System.out.println("From UserManagement - Activation Email sent");	 
				 
	 
			 }
		} catch (SQLException e) {
			System.out.println("UserManagement DAO - INSERT_USERS_SQL" + e);

		}
	}

	public UserBean selectUser(int userid) {
		UserBean userBean = null;
		try (Connection connection = ConnectionDB.getInstance().getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
			preparedStatement.setInt(1, userid);
			System.out.println("UserManagement DAO - SELECT_USER_BY_ID" + preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {

				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String userName = rs.getString("userName");
				String password = rs.getString("password");
				String email = rs.getString("email");
				String telephone = rs.getString("telephone");
				int admin = rs.getInt("admin");
				int active = rs.getInt("active");
				userBean = new UserBean(userid, firstName, lastName, userName, password, email, telephone, admin, active);
			}

		} catch (SQLException e) {
			System.out.println("UserManagemetn - SELECT_USER_BY_ID" + e);
		}
		return userBean;
	}

	public List<UserBean> selectAllUsers() {
		List<UserBean> users = new ArrayList<>();

		try (Connection connection = ConnectionDB.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {

			System.out.println("UserManagement DAO - SELECT_ALL_USERS " + preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {

				int userid = rs.getInt("userid");
				String firstName = rs.getString("firstname");
				String lastName = rs.getString("lastname");
				String userName = rs.getString("username");
				String password = rs.getString("password");
				String email = rs.getString("email");
				String telephone = rs.getString("telephone");
				int admin = rs.getInt("admin");
				int active = rs.getInt("active");
				users.add(
						new UserBean(userid, firstName, lastName, userName, password, email, telephone, admin, active));
			}

		} catch (SQLException e) {
			System.out.println("UserManagemetn - SELECT_ALL_USERS" + e);
		}
		return users;
	}
	
    public boolean deleteUser(int userid) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = ConnectionDB.getInstance().getConnection();
        	PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
            statement.setInt(1, userid);
            rowDeleted = statement.executeUpdate() > 0;
            
            System.out.println("UserManagement DAO - DELETE_USERS_SQL" + statement);
        }
        return rowDeleted;
    }
	
    public boolean updateUser(UserBean userBean) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = ConnectionDB.getInstance().getConnection();
        	PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS_SQL);) {
        	
        	    	
			preparedStatement.setString(1, userBean.getFirstName());
			preparedStatement.setString(2, userBean.getLastName());
			preparedStatement.setString(3, userBean.getUserName());
			preparedStatement.setString(4, userBean.getEmail());
			preparedStatement.setString(5, userBean.getTelephone());
			preparedStatement.setInt(6, userBean.getUserID());    
			
            rowUpdated = preparedStatement.executeUpdate() > 0;
            
            System.out.println("UserManagement DAO - UPDATE_USERS_SQL" + preparedStatement);
        }
        return rowUpdated;
    }        
}
