package com.core.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.core.DAO.RecordDao;
import com.core.model.RecordBean;
import com.registration.model.UserBean;

@WebServlet("/RecordServlet")
public class RecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public RecordServlet() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		String category = request.getParameter("category");
		String record = request.getParameter("record");
		Double amount = (double) Integer.parseInt(request.getParameter("amount"));
	
		String dateString = request.getParameter("date");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = sdf.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
			date = new Date(System.currentTimeMillis());
		}
		System.out.println("dateString: " + dateString);
		System.out.println("date: " + date);
		
	    Double tax = (double) Integer.parseInt(request.getParameter("taxAmount"));
		System.out.println("From Records Servlet - Set " + tax);
		String comment = request.getParameter("comment");
		
		RecordBean recordBean = new RecordBean(0, category, record, amount, date, tax, comment,  0);
		recordBean.setCategory(category);
		recordBean.setRecord(record);
		recordBean.setAmount(amount);
		recordBean.setDate(date);
		recordBean.setTaxAmount(tax);
		recordBean.setComment(comment);
		
		UserBean userBean = (UserBean) request.getSession().getAttribute("session_user");
		RecordDao recordDao = new RecordDao();
		recordDao.addRecord(recordBean, userBean);

		System.out.println("From Records Servlet - Set " + comment);
		

		
		try {
			if (recordDao.equals(true)) {
				RequestDispatcher rd = request.getRequestDispatcher("Home.jsp");
				rd.forward(request, response);

				System.out.println("From RecordServlet - record added");
			} else {

				response.sendRedirect("Home.jsp");
			}

		} catch (Exception ex) {
			System.out.println("User registered" + ex);
		}
	}

}
