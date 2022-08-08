package jsp.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/jsp_request")
public class JspRequestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = "/WEB-INF/jsp/jsp_request.jsp";
		
		System.out.println("param_name : " + request.getParameter("param_name"));
		
		if(request.getParameterValues("param_chk") != null) {
			System.out.println("param_chk : " + Arrays.asList(request.getParameterValues("param_chk")));
		}
		
		Iterator<String> iter = request.getParameterNames().asIterator();
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
		
		request.getRequestDispatcher(view).forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = "/WEB-INF/jsp/jsp_request.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}

}
