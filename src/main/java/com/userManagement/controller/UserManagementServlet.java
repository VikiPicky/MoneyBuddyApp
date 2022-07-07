package com.userManagement.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.registration.model.UserBean;
import com.userManagement.DAO.UserManagementDAO;

@WebServlet("/")
public class UserManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserManagementDAO userManagementDAO;

	public UserManagementServlet() {
		super();
	}

	public void init() {
		userManagementDAO = new UserManagementDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getServletPath();

		System.out.println("UserManagemetn servlet doGet method - top: " + action);
		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertUser(request, response);
				System.out.println("UserManagemetn servlet INSERT");
				break;
			case "/delete":
				deleteUser(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateUser(request, response);
				break;
			case "/list":
				listUser(request, response);
				System.out.println("UserManagemetn servlet LIST USER");
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<UserBean> listUser = userManagementDAO.selectAllUsers();
		request.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("UserManagement-user-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("UserManagement-user-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int userid = Integer.parseInt(request.getParameter("userid"));
		UserBean existingUser = userManagementDAO.selectUser(userid);
		RequestDispatcher dispatcher = request.getRequestDispatcher("UserManagement-user-update.jsp");
		request.setAttribute("user", existingUser);
		dispatcher.forward(request, response);
	}

	private void insertUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String userName = request.getParameter("userName");
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");

		UserBean newUser = new UserBean(firstName, lastName, userName, email, telephone);
		userManagementDAO.insertUser(newUser);
		response.sendRedirect("list");
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

		System.out.println("UserManagemetn servlet UPDATE [" + request.getParameter("userID") + "]");

		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String userName = request.getParameter("userName");
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		
	int userid = Integer.parseInt(request.getParameter("userID"));
		UserBean book = new UserBean(userid, firstName, lastName, userName, email, telephone);
		userManagementDAO.updateUser(book, userid);
		response.sendRedirect("list");
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int userid = Integer.parseInt(request.getParameter("userid"));
		userManagementDAO.deleteUser(userid);
		response.sendRedirect("list");
	}

}
