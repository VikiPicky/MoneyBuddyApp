package com.registration.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.registration.DAO.UserDao;
import com.registration.model.SignInBean;
import com.registration.model.UserBean;

@WebServlet("/SignInServlet")
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SignInServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("SignInServlet: In");

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		System.out.println("SignInServlet: instances created");

		// hash the password to compare with hashed pwd stored in db

		String hashedLoginPwd;
		try {
			hashedLoginPwd = createHash(password);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new ServletException("Pwd not hashed!");
		}

		UserDao userDao = new UserDao();

		UserBean userBean = userDao.getActiveUser(email, hashedLoginPwd);

		if (userBean != null) {
			HttpSession session = request.getSession(true);
			session.setAttribute("session_user", userBean);
			if (userBean.getAdmin() == 1) {
				response.sendRedirect("UserManagement-user-form.jsp");
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("Home.jsp");
				rd.forward(request, response);
			}
		} else
			response.sendRedirect("index.html");
	}


	private static String createHash(String password) throws NoSuchAlgorithmException {

		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] messageDigest = md.digest(password.getBytes());
		BigInteger no = new BigInteger(1, messageDigest);

		String hashtext = no.toString(16);
		while (hashtext.length() < 32) {
			hashtext = "0" + hashtext;
		}
		return hashtext;

	}
}
