package com.registration.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ConnectionDB {

	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	private static final String CONN_STRING = "jdbc:mysql://localhost/";
	
	static Connection connection;
	
	public static Connection getConnection() {
		try {
			System.out.println("Database 1");
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
			System.out.println("Database 2");
				
			Statement stmt = connection.createStatement();
			
			String sql = 
					"CREATE SCHEMA IF NOT EXISTS moneybuddy;"; 
			stmt.executeUpdate(sql);
			
			String sql2 = "USE moneybuddy;";
			stmt.executeUpdate(sql2);
				
			String sql3 = "CREATE TABLE IF NOT EXISTS user (UserID INT NOT NULL auto_increment, FirstName VARCHAR(45) NOT NULL, LastName VARCHAR(45) NOT NULL, UserName VARCHAR(45) NOT NULL, Password VARCHAR(45) NOT NULL, email VARCHAR(45) NOT NULL, Telephone VARCHAR(45) NOT NULL, Admin INT(1) NOT NULL, Active INT(1) NOT NULL, PRIMARY KEY (UserID));";
			stmt.executeUpdate(sql3);
					
			String sql4 = "CREATE TABLE IF NOT EXISTS income (IncomeID INT NOT NULL auto_increment,Date DATE NOT NULL,Amount DECIMAL(10,2) NOT NULL, UserID INT NOT NULL, PRIMARY KEY (IncomeID), CONSTRAINT FK_INCOME_USER FOREIGN KEY (UserID) REFERENCES USER(UserID));";
			stmt.executeUpdate(sql4);
				
			String sql5= "CREATE TABLE IF NOT EXISTS category (CategoryID INT NOT NULL auto_increment, CategoryName VARCHAR(45) NOT NULL, `UserID` INT NOT NULL, PRIMARY KEY (`CategoryID`), CONSTRAINT FK_USER FOREIGN KEY (UserID) REFERENCES USER(UserID));";
			stmt.executeUpdate(sql5);
			
			String sql6= "CREATE TABLE IF NOT EXISTS record (RecordID INT NOT NULL auto_increment,Date DATE NOT NULL,Amount DECIMAL(10,2) NOT NULL,Comment LONGTEXT NULL DEFAULT NULL, TaxAmount DECIMAL(2,2) NULL DEFAULT NULL,CategoryID INT NOT NULL,  PRIMARY KEY (RecordID), CONSTRAINT FK_CATEGORY FOREIGN KEY (CategoryID) REFERENCES CATEGORY(CategoryID));";
			stmt.executeUpdate(sql6);
			System.out.println("From My Connection Class - CONNECTED and Created");
			
			
			System.out.println("Database created successfully...");  
		} catch (Exception ex) {
			System.out.println("From My Connection Class" + ex);
		}
		return connection;
	}
}
