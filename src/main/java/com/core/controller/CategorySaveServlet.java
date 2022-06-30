package com.core.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.core.DAO.CategoryDao;
import com.core.model.CategoryBean;
import com.registration.model.UserBean;

@WebServlet("/CategorySaveServlet")
public class CategorySaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CategorySaveServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		String category = request.getParameter("category");

		CategoryBean categoryBean = new CategoryBean(0, category, 0);
		categoryBean.setCategoryName(category);
		System.out.println("From Category Servlet - Set");

		UserBean userBean = (UserBean) request.getSession().getAttribute("session_user");
		CategoryDao dao = new CategoryDao();
		dao.addCategory(categoryBean, userBean);
	}

}
