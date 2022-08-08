package board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.EmpBoardDTO;
import board.service.EmpBoardService;
import emps.model.EmpDTO;

@WebServlet("/board/modify")
public class EmpBoardModifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private EmpBoardService service = new EmpBoardService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String id = request.getParameter("id");
		
		EmpDTO empData = (EmpDTO)session.getAttribute("loginData");
		
		EmpBoardDTO data = service.getData(Integer.parseInt(id));
		
		if(data.getEmpId() == empData.getEmpId()) {
			request.setAttribute("data", data);
			
			String view = "/WEB-INF/jsp/board/modify.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(view);
			rd.forward(request, response);
		} else {
			// 리다이렉트 처리 혹은 에러페이지로 포워딩
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String id = request.getParameter("id");
		
		EmpDTO empData = (EmpDTO)session.getAttribute("loginData");
		
		EmpBoardDTO data = service.getData(Integer.parseInt(id));
		
		if(data.getEmpId() == empData.getEmpId()) {
			data.setTitle(request.getParameter("title"));
			data.setContent(request.getParameter("content"));
			
			boolean result = service.modify(data);
			if(result) {
				response.sendRedirect(request.getContextPath() + "/board/detail?id=" + data.getId());
			} else {
				doGet(request, response);
			}
		} else {
			// 리다이렉트 혹은 에러페이지로 포워딩
		}
	}

}
