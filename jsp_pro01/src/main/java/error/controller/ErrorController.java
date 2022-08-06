package error.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/error")
public class ErrorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String view = "/WEB-INF/error/error.jsp";
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// request.getSession().getAttribute("error") : 기존의 어떤 에러가 발생했었는지 확안해줌
		request.setAttribute("error", request.getSession().getAttribute("error"));
		request.getSession().removeAttribute("error"); // 에러를 더이상 남겨줄 필요 없으므로 제거
		
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}


}
