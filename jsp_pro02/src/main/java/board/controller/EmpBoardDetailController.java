package board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.EmpBoardDTO;
import board.service.EmpBoardService;
import comment.model.CommentDTO;
import comment.service.CommentService;
import common.util.Paging;
import emps.model.EmpDTO;
import emps.service.EmpService;

@WebServlet("/board/detail")
public class EmpBoardDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private EmpBoardService service = new EmpBoardService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = "/WEB-INF/jsp/board/detail.jsp";
		
		String id = request.getParameter("id");
		
		EmpBoardDTO data = service.getData(Integer.parseInt(id));
		
		if(data != null) {		
			service.incViewCnt(request.getSession(), data);
			EmpService empService = new EmpService();
			CommentService commentService = new CommentService();
			
			EmpDTO empData = empService.getId("" + data.getEmpId());
			// List<CommentDTO> commentDatas = commentService.getDatas(data.getId());
			String page = request.getParameter("page");
			String limit = "5";
			page = page == null ? "1" : page;
			Paging commentPage = commentService.getPage(page, limit, data.getId());
			
			// data.setContent(data.getContent().replace("\r\n", "<br>"));
			
			request.setAttribute("data", data);
			request.setAttribute("empData", empData);
			request.setAttribute("commentPage", commentPage);
			
			RequestDispatcher rd = request.getRequestDispatcher(view);
			rd.forward(request, response);
		} else {
			// 별도의 페이지로 데이터가 없음을 알려야 함.
			// 1. 포워드     : URL 주소는 바뀌지 않음
			// 2. 리다이렉트 : URL 주소가 바뀜
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=utf-8");
		String id = request.getParameter("id");
		EmpBoardDTO data = service.getData(Integer.parseInt(id));
		
		PrintWriter out = response.getWriter();
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		if(data != null) {
			service.incLike(request.getSession(), data);
			sb.append(String.format("\"%s\": \"%s\",", "code", "success"));
			sb.append(String.format("\"%s\": %d", "likeCnt", data.getLike()));
		}
		sb.append("}");
		
		out.append(sb.toString());
		out.flush();
	}
}
