package com.core.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.core.DAO.RecordDao;
import com.core.model.RecordBean;
import com.registration.model.UserBean;

@WebServlet("/Record/*")
public class RecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RecordServlet() {
		super();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());

		String action = request.getRequestURI();

		System.out.println("RecordsServlet doGet method - top: " + action);

		try {
			switch (action) {
			case "/TestMaven/Record/insertRecord":		
				insertRecord(request, response);
				break;
			case "/TestMaven/Record/deleteRecord":
				deleteRecord(request, response);
				break;
			case "/TestMaven/Record/editRecord":
				showRecordEditForm(request, response);
				break;
			case "/TestMaven/Record/updateRecord":
				updateRecord(request, response);
				break;
			case "/TestMaven/Record/listRecord":
				System.out.println("Record servlet listRecord  --IN");
				listRecord(request, response);
	
				break;
				
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listRecord(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<RecordBean> listRecord = RecordDao.selectAllRecords();
		
		System.out.println("Record servlet listRecord  -- List<RecordBean>");
		
		request.setAttribute("listRecord", listRecord);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/RecordTable.jsp");
		dispatcher.forward(request, response);
	}

	private void showRecordEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		
		int recordId = Integer.parseInt(request.getParameter("recordId"));
		
		System.out.println("Record servlet howRecordEditForm  -- IN RecordID" + recordId);
		
		RecordBean existingRecord = RecordDao.selectRecord(recordId);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Record-update.jsp");
		request.setAttribute("record", existingRecord);
		dispatcher.forward(request, response);
	}

	private void insertRecord(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		
		String category = request.getParameter("category");		
		String record = request.getParameter("record");
		Double amount = Double.parseDouble(request.getParameter("amount"));
		
		String dateString = request.getParameter("date");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = sdf.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
			date = new Date(System.currentTimeMillis());
		}
		Double taxAmount = Double.parseDouble(request.getParameter("taxAmount"));
		String comment = request.getParameter("comment");
		
		UserBean userBean = (UserBean) request.getSession().getAttribute("session_user");
		Integer userId = userBean.getUserID();

		RecordBean newRecord = new RecordBean(0, category, record, amount, date, comment, taxAmount, userId);
		RecordDao recordDao = new RecordDao();
		recordDao.insertRecord(newRecord);
		response.sendRedirect("listRecord");
	}

	private void updateRecord(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		
		UserBean userBean = (UserBean) request.getSession().getAttribute("session_user");
		Integer userId = userBean.getUserID();

		System.out.println("Records Servlet UPDATE [" + request.getParameter("recordId") + "]");

		String record = request.getParameter("record");
		Double amount = Double.parseDouble(request.getParameter("amount"));
		Double taxAmount = Double.parseDouble(request.getParameter("taxAmount"));
		String comment = request.getParameter("comment");
		int recordId = Integer.parseInt(request.getParameter("recordId"));

		RecordBean updatableRecord = new RecordBean(recordId, null, record, amount, null, comment, taxAmount, userId);
		RecordDao.updateRecord(updatableRecord);
		response.sendRedirect("listRecord");
	}

	private void deleteRecord(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int recordId = Integer.parseInt(request.getParameter("recordId"));
		RecordDao.deleteRecord(recordId);
		response.sendRedirect("listRecord");
	}

}
