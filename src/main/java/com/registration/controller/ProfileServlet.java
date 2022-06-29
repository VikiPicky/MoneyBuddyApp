package com.registration.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.registration.DAO.ProfileDao;
import com.registration.model.UserBean;

@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProfileServlet() {
		super();
		System.out.println("Profile Servlet 1"  );
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("Profile Servlet IN"  );
		response.getWriter().append("Served at: ").append(request.getContextPath());
		UserBean userBean = (UserBean) request.getSession().getAttribute("session_user");
		System.out.println("Hello, " + userBean.getFirstName());
		
		request.setAttribute("user", userBean);

		request.getRequestDispatcher("/Profile.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
