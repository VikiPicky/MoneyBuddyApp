package com.registration.DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDB {

	private static final String USERNAME = "dbuser";
	private static final String PASSWORD = "dbpassword";
	private static final String CONN_STRING = "jdbc:mysql://localhost/moneybuddy";
	
	static Connection connectionDB;
	
	public static Connection getConnection() {
		try {
			connectionDB = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
			System.out.println("From My Connection Class - CONNECTED");
		} catch (Exception ex) {
			System.out.println("From My Connection Class" + ex);
		}
		return connectionDB;
	}
}
