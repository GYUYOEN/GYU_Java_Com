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
			if(session.getAttribute("pageCount") == null) { // 세션에 저장된 것이 없을 경우
				limit = "5";
			}
		}
		
		if(page == null) page = "1";
		
		
		// 작성자 검색, 내용+제목 검색, 내용 검색 할 수 있게 만들어 보기 (xml에 WHERE절을 사용하여 가능, 내용+제목는 WHERE절에 OR 사용)
		Paging pageData = null;
		// 조회
		if(request.getParameter("search") == null) { // 파라미터가 없을 때
			pageData = service.getPage(page, limit);
		} else {
			if(request.getParameter("search").isEmpty()) { // 파라미터가 공백일 경우	
				pageData = service.getPage(page, limit);
			} else { // 조회한 파라미터가 있을 때
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
