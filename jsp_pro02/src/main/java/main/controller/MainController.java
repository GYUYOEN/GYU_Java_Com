package main.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dept.model.DeptDTO;
import dept.service.DeptService;

@WebServlet("")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DeptService deptService = new DeptService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = (String)request.getAttribute("view");
		
		RequestDispatcher rd = null;
		if(request.getSession().getAttribute("loginData") == null) {
			List<DeptDTO> deptList = deptService.getAll();
			request.setAttribute("deptList", deptList);
			rd = request.getRequestDispatcher(view + "/login/login.jsp");
		} else {
			rd = request.getRequestDispatcher(view + "/index.jsp");
		}
		rd.forward(request, response);
	}

}
