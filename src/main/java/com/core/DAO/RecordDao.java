package com.core.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;

import com.core.model.RecordBean;
import com.registration.DAO.ConnectionDB;
import com.registration.model.UserBean;

public class RecordDao {

	private static final String INSERT_RECORD_SQL = "INSERT INTO RECORD"
			+ "  (category, record, amount, date, comment, taxamount, userid) VALUES " + " (?, ?, ?, ?, ?, ?, ?);";

	private static final String SELECT_RECORD_BY_ID = "SELECT * from RECORD where recordid=? ;";
	private static final String SELECT_ALL_RECORDS = "SELECT * from RECORD;";
	private static final String DELETE_RECORD_SQL = "DELETE from RECORD where recordid =? ;";
	private static final String UPDATE_RECORD_SQL = "UPDATE RECORD SET record=?, amount=?, comment=?, taxamount=? WHERE recordid=?;";

	public void insertRecord(RecordBean recordBean) {

		System.out.println(INSERT_RECORD_SQL);

		try (	Connection connection = ConnectionDB.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_RECORD_SQL)) {
			
			String category = recordBean.getCategory();
			String record = recordBean.getRecord();
			Double amount = (Double) recordBean.getAmount();
			Date date = recordBean.getDate();
			Double taxAmount = (Double) recordBean.getTaxAmount();
			String comment = recordBean.getComment();		
			Integer userId = recordBean.getUserId();
			
			preparedStatement.setString(1, category);
			preparedStatement.setString(2, record);
			preparedStatement.setDouble(3, amount);
			preparedStatement.setDate(4, new java.sql.Date(date.getTime()));
			preparedStatement.setString(5, comment);
			preparedStatement.setDouble(6, taxAmount);
			preparedStatement.setInt(7, userId);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("RecordServlet DAO - INSERT_RECORD_SQL" + e);
		}
	}

	
	public static RecordBean selectRecord(int recordId) {
		RecordBean recordBean = null;
		try (Connection connection = ConnectionDB.getInstance().getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_RECORD_BY_ID);) {
			preparedStatement.setInt(1, recordId);
			System.out.println("RecordServlet DAO - SELECT_RECORD_BY_ID" + preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {

				String category = rs.getString("category");
				String record = rs.getString("record");
				Double amount = rs.getDouble("amount");
				
				String dateString = rs.getString("date");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date;
				try {
					date = sdf.parse(dateString);
				} catch (ParseException e) {
					e.printStackTrace();
					date = new Date(System.currentTimeMillis());
				}
				String comment = rs.getString("comment");
				Double taxAmount = rs.getDouble("taxamount");				
				int userid = rs.getInt("userid");

				recordBean = new RecordBean(recordId, category, record, amount, date, comment, taxAmount, userid );
			}

		} catch (SQLException e) {
			System.out.println("RecordServlet - SELECT_RECORD_BY_ID" + e);
		}
		return recordBean;
	}
	
	public static List<RecordBean> selectAllRecords() {
		List<RecordBean> records = new ArrayList<>();

		try (Connection connection = ConnectionDB.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_RECORDS);) {

			System.out.println("RecordServlet - SELECT_ALL_RECORDS " + preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				int recordId = rs.getInt("recordid");
				String category = rs.getString("category");
				String record = rs.getString("record");
				Double amount = rs.getDouble("amount");
				String dateString = rs.getString("date");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date;
				try {
					date = sdf.parse(dateString);
				} catch (ParseException e) {
					e.printStackTrace();
					date = new Date(System.currentTimeMillis());
				}
				String comment = rs.getString("comment");
				Double taxAmount = rs.getDouble("taxamount");				
				int userid = rs.getInt("userid");
				records.add(
						new RecordBean(recordId, category, record, amount, date, comment, taxAmount, userid));
			}

		} catch (SQLException e) {
			System.out.println("UserManagemetn - SELECT_ALL_USERS" + e);
		}
		return records;
	}
	
    public static boolean deleteRecord(int recordId) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = ConnectionDB.getInstance().getConnection();
        	PreparedStatement statement = connection.prepareStatement(DELETE_RECORD_SQL);) {
            statement.setInt(1, recordId);
            rowDeleted = statement.executeUpdate() > 0;
            
            System.out.println("RecordServlet DAO - DELETE_RECORD_SQL" + statement);
        }
        return rowDeleted;
    }
    
    public static boolean updateRecord(RecordBean recordBean) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = ConnectionDB.getInstance().getConnection();
        	PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_RECORD_SQL);) {
        	
        	preparedStatement.setString(1, recordBean.getRecord());
			preparedStatement.setDouble(2, recordBean.getAmount());			
			preparedStatement.setString(3, recordBean.getComment());
			preparedStatement.setDouble(4, recordBean.getTaxAmount());
			preparedStatement.setInt(5, recordBean.getRecordId());
			
			rowUpdated = preparedStatement.executeUpdate() > 0;
            
            System.out.println("UserManagement DAO - UPDATE_RECORD_SQL" + preparedStatement);
        }
        return rowUpdated;
    }        
}
