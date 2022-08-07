package board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.EmpBoardDTO;
import board.service.EmpBoardService;
import common.util.Paging;
import common.util.Parameter;

@WebServlet("/board")
public class EmpBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private EmpBoardService service = new EmpBoardService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String view = "/WEB-INF/jsp/board/list.jsp";
		
		String page = request.getParameter("page");
		String limit = null; // 보여질 데이터 수
		
		if(request.getParameter("pgc") != null) {
			limit = request.getParameter("pgc");
			session.setAttribute("pageCount", limit);
		} else {
			limit = (String)session.getAttribute("pageCount");
			if(session.getAttribute("pageCount") == null) {
				limit = "5";
			}
		}
		
		if(page == null) page = "1";
		
		Paging pageData = null;
		if(request.getParameter("search") == null) {
			pageData = service.getPage(page, limit);
		} else {
			if(request.getParameter("search").isEmpty()) {
				pageData = service.getPage(page, limit);
			} else {
				pageData = service.getPage(page, limit, request.getParameter("search"));
			}
		}
		
		if(pageData.getPageDatas().size() <= 0) {
			pageData = service.getPage("1", limit);
		}
		
		request.setAttribute("datas", pageData);
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
}
