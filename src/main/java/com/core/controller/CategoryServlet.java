package com.core.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale.Category;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.core.DAO.CategoryDao;
import com.core.model.CategoryBean;
import com.registration.model.UserBean;

@WebServlet("/CategoryServlet")
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CategoryServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		listCategory(request, response);
	}
	private void listCategory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
		System.out.println("From CatagoryServlet- IN");
		
		CategoryDao categoryDao = new CategoryDao();
		
		try {
			UserBean userBean = (UserBean) request.getSession().getAttribute("session_user");
            List<CategoryBean> listCatagory = categoryDao.list(userBean);
            request.setAttribute("listCategory", listCatagory);
 
            RequestDispatcher dispatcher = request.getRequestDispatcher("Home.jsp");
            dispatcher.forward(request, response);
 
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
		
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

//		String category = request.getParameter("category");
//
//		CategoryBean categoryBean = new CategoryBean(0, category, 0);
//		categoryBean.setCategoryName(category);
//		System.out.println("From Category Servlet - Set");
//
//		UserBean userBean = (UserBean) request.getSession().getAttribute("session_user");
//		CategoryDao dao = new CategoryDao();
//		dao.addCategory(categoryBean, userBean);
		
		
	    int categoryId = Integer.parseInt(request.getParameter("category"));
	    request.setAttribute("selectedCatId", categoryId);
	    listCategory(request, response);
	}
}
