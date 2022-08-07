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
import javax.servlet.http.HttpSession;

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
			
			if(!isViewed) { // 안 봤으면
				viewList.add(id); // id 추가하고
				Cookie cookie = new Cookie("boardView", String.join("/", viewList)); // 봤다고 설정	
				cookie.setMaxAge(60*60*24*7); // 일주일동안 조회수 증가 불가
				response.addCookie(cookie);
				service.incViewCnt(data);
			}
			*/
			service.incViewCnt(request.getSession(), data);
			EmpService empService = new EmpService();
			CommentService commentService = new CommentService();
			
			EmpDTO empData = empService.getId("" + data.getEmpId()); // 작성자 : 100 -> Steven King
//			List<CommentDTO> commentDatas = commentService.getDatas(data.getId());
			
			String page = request.getParameter("page");
			String limit = "5";
			page = page == null ? "1" : page;
			Paging commentPage = commentService.getPage(page, limit, data.getId());
			
//			data.setContent(data.getContent().replace("\r\n", "<br>")); // 게시물 내용을 개행시켜주기 위한 로직
			
			request.setAttribute("data", data);
			request.setAttribute("empData", empData);
			request.setAttribute("commentPage", commentPage);
			
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
			 service.incLike(request.getSession(), data);
			 sb.append(String.format("\"%s\" : \"%s\",", "code", "success"));
			 sb.append(String.format("\"%s\" : %d", "likeCnt", data.getLike()));
		}
		sb.append("}");
		
		out.append(sb.toString());
		out.flush();
	}
	
}
/*
 * 조회수 
 * 		사용자(사원/회원)는 하나의 게시물당 1개의 조회수만 올릴 수 있다
 * 		추가 조회수를 올리기 위해서는 일주일 뒤에 다시 해당 게시물을 조회 해야 한다.
 */
