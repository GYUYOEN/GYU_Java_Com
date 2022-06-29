package main.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/* annotation (어노테이션)
 * 		- WebServlet 에 적혀져 있는 이름("")으로 접근 (localhost:8080/jsp01/MainController)
 * 		- 구성이 편함, 관리는 불현 
 */

@WebServlet("") // 어노테이션에 해당하는 이름의 class를 찾아가면됨
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		// getRequestDispatcher(view) : jsp 파일을 이용하여 문자열 생성
		String view = "/WEB-INF/jsp/index.jsp";
		request.getRequestDispatcher(view).forward(request, response);
		// request.getRequestDispatcher(view) : 사용할 jsp 파일을 알려줌 (view(index.jsp) 가 탭플릿이 됨) -> servlet은 모름으로 알려줌
		// forward(request, response) : request(요청정보), response(응답정보) 같이 jsp 파일에 전달
		
		
		// getWriter() 을 이용하여 바로 문자열 생성
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		// request.getContextPath() : 자신의 context path 정보를 알 수 있음
	} 
}