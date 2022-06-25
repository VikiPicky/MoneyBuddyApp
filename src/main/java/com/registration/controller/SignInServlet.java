package com.registration.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.registration.DAO.SignInDao;
import com.registration.model.SignInBean;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
		doGet(request, response);

		System.out.println("SignInServlet: In");

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		System.out.println("SignInServlet: instances created");

		// hash the password to compare with hasghed pwd stored in db

		String hashedLoginPwd;
		try {
			hashedLoginPwd = createHash(password);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new ServletException("Извините, всё сломалось.!");
		}

		// SignIn Bean Code
		SignInBean signInbean = new SignInBean();
		signInbean.setEmail(email);
		signInbean.setPassword(password);
		signInbean.setHashedLoginPwd(hashedLoginPwd);

		// SignIn DAO
		// SignInDao signInDao = new SignInDao();
		String daoStr = SignInDao.SignInCheck(signInbean);

		if (daoStr.equals("SUCCESS")) {
			HttpSession session = request.getSession(true);
			session.setAttribute("session_user", email);
			RequestDispatcher rd = request.getRequestDispatcher("Home.jsp");
			rd.forward(request, response);
		} else {
			response.sendRedirect("index.html");
		}

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
