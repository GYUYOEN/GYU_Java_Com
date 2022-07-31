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
			/* 쿠키로 
			Cookie cookies[] = request.getCookies();
			List<String> viewList = new ArrayList<String>();
			for(Cookie c: cookies) {
				if(c.getName().equals("boardView")) {
					viewList = new ArrayList<String>(Arrays.asList(c.getValue().split("/")));
				}
			}
			
			boolean isViewed = false;
			for(String s: viewList) {
				if(s.equals(id)) {
					isViewed = true;
				}
			}
			
			if(!isViewed) {
				viewList.add(id);
				Cookie cookie = new Cookie("boardView", String.join("/", viewList));			
				cookie.setMaxAge(60*60*24*7);
				response.addCookie(cookie);
				service.incViewCnt(data);
			}
			*/
			service.incViewCnt(data);
			EmpService empService = new EmpService();
			EmpDTO empData = empService.getId("" + data.getEmpId());
			
			request.setAttribute("data", data);
			request.setAttribute("empData", empData);
			
			RequestDispatcher rd = request.getRequestDispatcher(view);
			rd.forward(request, response);
		} else {
			// 별도의 페이지로 데이터가 없음을 알려야 함
			// 1. 포워드		: URL 주소는 바뀌지 않음
			// 2. 리다이렉트	: URL 주소가 바뀜
			// 만들어보기
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
			 service.incLike(data);
			 sb.append(String.format("\"%s\" : \"$s\"", "code", "success"));
		}
		sb.append("}");
		
		out.append(sb.toString());
		out.flush();
	}
	
}
