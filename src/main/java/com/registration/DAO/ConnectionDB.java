package com.registration.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDB {

	private final String USERNAME = "root";
	private final String PASSWORD = "";
	private final String CONN_STRING = "jdbc:mysql://localhost/";
	private static ConnectionDB connectionDB;
	private static boolean initialized = false;

	private ConnectionDB() {
	}

	private void init() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);

			Statement stmt = connection.createStatement();

			String SQL_CREATE_SCHEMA = "CREATE SCHEMA IF NOT EXISTS moneybuddy;";
			stmt.executeUpdate(SQL_CREATE_SCHEMA);

			String SQL_USE_SCHEMA = "USE moneybuddy;";
			stmt.executeUpdate(SQL_USE_SCHEMA);

			String SQL_CREATE_USER = "CREATE TABLE IF NOT EXISTS user (UserID INT NOT NULL auto_increment, FirstName VARCHAR(45) NOT NULL, LastName VARCHAR(45) NOT NULL, UserName VARCHAR(45) NOT NULL, Password VARCHAR(45) NOT NULL, email VARCHAR(45) NOT NULL, Telephone VARCHAR(45) NOT NULL, Admin INT(1) NOT NULL, Active INT(1) NOT NULL, PRIMARY KEY (UserID));";
			stmt.executeUpdate(SQL_CREATE_USER);

			String SQL_CREATE_INCOME = "CREATE TABLE IF NOT EXISTS income (IncomeID INT NOT NULL auto_increment,Date DATE NOT NULL,Amount DECIMAL(10,2) NOT NULL, UserID INT NOT NULL, PRIMARY KEY (IncomeID), CONSTRAINT FK_INCOME_USER FOREIGN KEY (UserID) REFERENCES USER(UserID));";
			stmt.executeUpdate(SQL_CREATE_INCOME);

			String SQL_CREATE_RECORD = "CREATE TABLE IF NOT EXISTS record (RecordID INT NOT NULL auto_increment,Category VARCHAR(45) NOT NULL, Record VARCHAR(45) NOT NULL, Amount DECIMAL(15,2) NOT NULL, Date DATE NOT NULL, Comment LONGTEXT NULL DEFAULT NULL, TaxAmount DECIMAL(10,2) NULL DEFAULT NULL,`UserID` INT NOT NULL,  PRIMARY KEY (RecordID), CONSTRAINT FK_USER FOREIGN KEY (UserID) REFERENCES USER(UserID));";
			stmt.executeUpdate(SQL_CREATE_RECORD);
			System.out.println("From My Connection Class - CONNECTED and Created");

			System.out.println("Database created successfully...");
		} catch (Exception ex) {
			System.out.println("From My Connection Class: " + ex.getMessage());
		}
	}

	public static synchronized ConnectionDB getInstance() {

		if (initialized) {
			return connectionDB;
		}
		connectionDB = new ConnectionDB();
		connectionDB.init();
		initialized = true;
		return connectionDB;

	}

	public Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
			Statement statement = connection.createStatement();
			String SQL_USE_SCHEMA = "USE moneybuddy;";
			statement.executeUpdate(SQL_USE_SCHEMA);
			return connection;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
