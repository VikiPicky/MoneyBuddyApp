package com.registration.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.registration.model.SignInBean;
import com.registration.model.UserBean;

public class UserDao {
	public UserDao() {}
		
		public String RegisterUser(UserBean userBean) {
			
			String firstName = userBean.getFirstName();
			String lastName = userBean.getLastName();
			String userName = userBean.getUserName();
			String password = userBean.getPassword();
			String email = userBean.getEmail();
			String telephone = userBean.getTelephone();
			
			Connection con = ConnectionDB.getConnection();
			 try {
				 String INSERT_USERS_SQL = "INSERT INTO USER" +
				            "  (firstName, lastName, userName, password, email, telephone, Admin, active ) VALUES " +
				            " (?, ?, ?, ?, ?, ?, ?, 0);";
				 
				 PreparedStatement preparedStatement = con.prepareStatement(INSERT_USERS_SQL);
				 preparedStatement.setString(1, firstName);
				 preparedStatement.setString(2, lastName);
				 preparedStatement.setString(3, userName);
				 preparedStatement.setString(4, password);
				 preparedStatement.setString(5, email);
				 preparedStatement.setString(6, telephone);
				 preparedStatement.setString(7, "0");
				 
				 int i = preparedStatement.executeUpdate();
				 if (i != 0) {
					
					 SendingEmail sendingEmail = new SendingEmail(email);
					
					 sendingEmail.sendEmail();
					 System.out.println("From UserDao - Email sent");
					 
					 return "SUCCESS";					 
				 }
				 
			 } catch(Exception ex) {
				 	System.out.println("RegistrationDao File" + ex);
			 }			
			return "ERROR";			
		}
		
		public UserBean getActiveUser(String email, String hashedLoginPwd) {

			Connection con = ConnectionDB.getConnection();

			System.out.println("From SignIN DAO - CONNECTED");

			try {
				String sqlQuery = "SELECT * from  user WHERE email=? AND password=? AND active=1;";
				
				System.out.println("From SignIN DAO - " + sqlQuery);
				PreparedStatement preparedStatement = con.prepareStatement(sqlQuery);
				preparedStatement.setString(1, email);
				preparedStatement.setString(2, hashedLoginPwd);
				
				System.out.println("From SignIN DAO - " + email + " " + hashedLoginPwd);

				
				ResultSet rs = preparedStatement.executeQuery();
				if (rs.last() && rs.getRow() > 0) {
					return new UserBean(
							rs.getInt("userID"),
							rs.getString("firstName"),
							rs.getString("lastName"),
							rs.getString("userName"),
							rs.getString("password"),
							rs.getString("email"),
							rs.getString("telephone"));

				}

			} catch (Exception ex) {
				System.out.println("SignInDao " + ex);
			}
			return null;
		} 
		
		
	}