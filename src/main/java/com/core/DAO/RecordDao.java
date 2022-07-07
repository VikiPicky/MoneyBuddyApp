package com.core.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;

import javax.servlet.RequestDispatcher;

import com.core.model.RecordBean;
import com.registration.DAO.ConnectionDB;
import com.registration.model.UserBean;

public class RecordDao {
	
	public boolean addRecord(RecordBean recordBean, UserBean userBean) {

		Connection con = ConnectionDB.getConnection();
		
		System.out.println("From RecordDao - CONNECTED " + userBean.getEmail());

		String category = recordBean.getCategory();
		String record = recordBean.getRecord();
		Double amount = (Double) recordBean.getAmount();
		Date date = recordBean.getDate();
		Double tax = (Double) recordBean.getTaxAmount();
		String comment = recordBean.getComment();

		System.out.println("From RecordDao - CONNECTED " + userBean.getEmail() + " " + record);

		try {
			

			String INSERT_RECORD_SQL = "INSERT INTO RECORD (category, record, amount, date, comment, taxAmount, UserId) VALUES (?, ?, ?, ?, ?, ?, ?);";

			PreparedStatement preparedStatement = con.prepareStatement(INSERT_RECORD_SQL);
			preparedStatement.setString(1, category);
			preparedStatement.setString(2, record);
			preparedStatement.setDouble(3, amount);
			preparedStatement.setDate(4, new java.sql.Date(date.getTime()));
			preparedStatement.setString(5, comment);
			preparedStatement.setDouble(6, tax);
			preparedStatement.setInt(7, userBean.getUserID());
			
			int recordAdded = preparedStatement.executeUpdate();
			System.out.println("From RecordDao - record added" + recordAdded);
			
		} catch (Exception ex) {
			System.out.println("From RecordDao" + ex);
		}
		return true;		
	} 
}

